package org.ecommerce.order.dto.mapper;

import org.ecommerce.order.dto.OrderLineDTO;
import org.ecommerce.order.model.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineDTO request) {
        return OrderLine.builder()
                .order(request.order())
                .quantity(request.quantity())
                .productId(request.productId())
                .build();
    }
}
