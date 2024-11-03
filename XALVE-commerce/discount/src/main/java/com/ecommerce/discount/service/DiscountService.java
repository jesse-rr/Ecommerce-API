package com.ecommerce.discount.service;

import com.ecommerce.discount.dto.DiscountDTO;
import com.ecommerce.discount.dto.mapper.DiscountMapper;
import com.ecommerce.discount.model.Discount;
import com.ecommerce.discount.repository.DiscountRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository repository;
    private final DiscountMapper mapper;

    public String createDiscount(DiscountDTO request) {
        return repository.save(mapper.toDiscount(request)).getDiscountId();
    }

    public DiscountDTO getDiscountByProductId(Long productId) {
        return repository.findByProductId(productId)
                .map(mapper::toDiscountDTO)
                .orElseThrow(() -> new NotFoundException("DISCOUNT DOES EXIST WITH PRODUCT BY ID :: "+productId));
    }

    @Scheduled(fixedRate = 3600000) // 1h
    public void isActiveRuntime() {
        repository.findAllByExpirationTimeBefore(LocalDateTime.now())
                .forEach(
                        discount -> discount.setActive(false)
                );
    }
}
