package org.ecommerce.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.order.controller.OrderLineController;
import org.ecommerce.order.dto.OrderLineDTO;
import org.ecommerce.order.dto.OrderRequestDTO;
import org.ecommerce.order.dto.mapper.OrderMapper;
import org.ecommerce.order.exception.CustomerNotFoundException;
import org.ecommerce.order.exception.TotalAmountException;
import org.ecommerce.order.notification.OrderKafkaProducer;
import org.ecommerce.order.notification.OrderNotificationRequest;
import org.ecommerce.order.openFeign.cart.CartClient;
import org.ecommerce.order.openFeign.cart.Items;
import org.ecommerce.order.openFeign.customer.CustomerClient;
import org.ecommerce.order.openFeign.discount.DiscountClient;
import org.ecommerce.order.openFeign.payment.PaymentClient;
import org.ecommerce.order.openFeign.payment.PaymentRequestDTO;
import org.ecommerce.order.openFeign.product.ProductClient;
import org.ecommerce.order.openFeign.product.ProductPurchaseRequest;
import org.ecommerce.order.openFeign.product.ProductPurchaseResponse;
import org.ecommerce.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineController orderLineController;
    private final PaymentClient paymentClient;
    private final ProductClient productClient;
    private final CustomerClient customerClient;
    private final CartClient cartClient;
    private final DiscountClient discountClient;
    private final OrderKafkaProducer kafkaProducer;

    public Long addOrder(OrderRequestDTO request) {
        var customerResponseDTO = customerClient.findCustomerByCpfCnpj(request.cpfCpnj())
                .orElseThrow(() -> new CustomerNotFoundException("CUSTOMER WITH CPF/CNPJ :: "+request.cpfCpnj()+" NOT FOUND"));

        List<ProductPurchaseResponse> productPurchaseResponses;
        if (request.isFromCart()) {
            var cartProducts = cartClient.getCart(customerResponseDTO.customerId());
            log.info("CART PRODUCTS = :: {} ::",cartProducts);
            List<ProductPurchaseRequest> purchaseRequests = new ArrayList<>();
            for (Items items : cartProducts) {
                purchaseRequests.add(new ProductPurchaseRequest(items.productId(), items.quantity()));
            }
            productPurchaseResponses = productClient.purchaseProducts(purchaseRequests);
        } else {
            productPurchaseResponses = productClient.purchaseProducts(request.products());
        }

        var totalAmount = calculateTotalAmount(productPurchaseResponses);
        var trackingNumber = "N-"+LocalDateTime.now().getYear()+String.format(String.valueOf(Math.round(Math.abs(Math.random() * 10000000))));

        var order = repository.save(mapper.toOrder(
                request,
                totalAmount,
                trackingNumber,
                customerResponseDTO.customerId())
        );
        for (ProductPurchaseRequest requests : request.products()) {
            orderLineController.addOrderLine(
                    new OrderLineDTO(
                            order,
                            requests.productId(),
                            requests.requestedQuantity()
                    )
            );
        }
        paymentClient.addPayment(
                new PaymentRequestDTO(
                        order.getOrderId(),
                        order.getTrackingNumber(),
                        totalAmount,
                        request.paymentMethod(),
                        request.currency(),
                        customerResponseDTO
                )
        );
        kafkaProducer.sendOrderNotificationRequest(
                new OrderNotificationRequest(
                        order.getTrackingNumber(),
                        order.getTotalAmount(),
                        order.getPaymentMethod(),
                        request.currency(),
                        customerResponseDTO,
                        productPurchaseResponses
                )
        );
        return order.getOrderId();
    }

    public BigDecimal calculateTotalAmount(List<ProductPurchaseResponse> productPurchaseResponses) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal itemTotal;
        for (ProductPurchaseResponse response : productPurchaseResponses) {
            var discount = discountClient.getDiscountByProductId(response.productId());
            BigDecimal subTotal = response.price().multiply(BigDecimal.valueOf(response.quantity()));

            if (discount.isActive()) {
                BigDecimal discountAmount = subTotal.multiply(BigDecimal.valueOf(discount.discountValue()));
                itemTotal = subTotal.subtract(discountAmount);
            } else {
                itemTotal = subTotal;
            }
            totalAmount = totalAmount.add(itemTotal);
        }
        if (totalAmount.toString().isEmpty() || totalAmount.equals(new BigDecimal(0))) {
            log.error("TOTAL AMOUNT FAILED TO MEET STANDARD AT CALCULATE_TOTAL_AMOUNT_ORDER_SERVICE:: <{}>",totalAmount);
            throw new TotalAmountException("TOTAL AMOUNT CANNOT BE NULL/EMPTY/ZERO");
        }
        return totalAmount;
    }
}
