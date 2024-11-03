package org.ecommerce.order.service;

import lombok.RequiredArgsConstructor;
import org.ecommerce.order.dto.OrderLineDTO;
import org.ecommerce.order.dto.mapper.OrderLineMapper;
import org.ecommerce.order.repository.OrderLineRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Long addOrderLine(OrderLineDTO request) {
        return repository.save(mapper.toOrderLine(request)).getOrderLineId();
    }
}
