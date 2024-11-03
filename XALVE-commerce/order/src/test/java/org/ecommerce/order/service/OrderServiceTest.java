package org.ecommerce.order.service;

import org.ecommerce.order.openFeign.discount.DiscountClient;
import org.ecommerce.order.openFeign.discount.DiscountDTO;
import org.ecommerce.order.openFeign.product.ProductPurchaseResponse;
import org.ecommerce.order.openFeign.product.ProductStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    @InjectMocks
    private OrderService service;

    @Mock
    private DiscountClient discountClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateTotalAmount_WithDiscount() {
        // Arrange
        ProductPurchaseResponse response1 = new ProductPurchaseResponse(1L, "Product 1", "Description 1", BigDecimal.valueOf(100), ProductStatus.ACTIVE, 2);
        ProductPurchaseResponse response2 = new ProductPurchaseResponse(2L, "Product 2", "Description 2", BigDecimal.valueOf(200), ProductStatus.ACTIVE, 1);

        // Mocking discounts
        when(discountClient.getDiscountByProductId(1L)).thenReturn(new DiscountDTO(1L, 0.10, LocalDateTime.now().plusDays(1), true));
        when(discountClient.getDiscountByProductId(2L)).thenReturn(new DiscountDTO(2L, 0.0, LocalDateTime.now().plusDays(1), false));

        List<ProductPurchaseResponse> responses = Arrays.asList(response1, response2);

        // Act
        BigDecimal totalAmount = service.calculateTotalAmount(responses);

        // Assert
        assertEquals(BigDecimal.valueOf(380.0), totalAmount);
    }

    @Test
    void testCalculateTotalAmount_WithoutDiscount() {
        // Arrange
        ProductPurchaseResponse response1 = new ProductPurchaseResponse(1L, "Product 1", "Description 1", BigDecimal.valueOf(150), ProductStatus.ACTIVE, 3);
        ProductPurchaseResponse response2 = new ProductPurchaseResponse(2L, "Product 2", "Description 2", BigDecimal.valueOf(300), ProductStatus.ACTIVE, 1);

        // Mocking discounts
        when(discountClient.getDiscountByProductId(1L)).thenReturn(new DiscountDTO(1L, 0.0, LocalDateTime.now().plusDays(1), false));
        when(discountClient.getDiscountByProductId(2L)).thenReturn(new DiscountDTO(2L, 0.0, LocalDateTime.now().plusDays(1), false));

        List<ProductPurchaseResponse> responses = Arrays.asList(response1, response2);

        // Act
        BigDecimal totalAmount = service.calculateTotalAmount(responses);

        // Assert
        assertEquals(BigDecimal.valueOf(750), totalAmount);
    }

}