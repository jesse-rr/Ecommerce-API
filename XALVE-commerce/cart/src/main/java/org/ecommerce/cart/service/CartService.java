package org.ecommerce.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.cart.model.Items;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final RedisTemplate<String, Items> redisTemplate;

    public void addOrUpdateProduct(String customerId, Items item) {
        List<Items> cartItems = getCart(customerId);
        boolean itemFound = false;

        for (int i = 0; i < cartItems.size(); i++) {
            Items cartItem = cartItems.get(i);
            if (cartItem.productId().equals(item.productId())) {
                int newQuantity = cartItem.quantity() + item.quantity();
                if (newQuantity <= 0) {
                    redisTemplate.opsForList().remove(customerId, 1, cartItem);
                    log.info("REMOVED {} FROM ID {}", cartItem, customerId);
                } else {
                    Items updatedItem = new Items(cartItem.productId(), newQuantity);
                    redisTemplate.opsForList().set(customerId, i, updatedItem);
                    log.info("UPDATED {} :: CART ID :: {} :: QUANTITY {}", updatedItem.productId(), customerId, newQuantity);
                }
                itemFound = true;
                break;
            }
        }

        if (!itemFound) {
            redisTemplate.opsForList().rightPush(customerId, item);
            log.info("ADDED ITEM {} WITH CART ID {}", item, customerId);
        }
    }


    public List<Items> getCart(String customerId) {
        if (customerId == null || customerId.isEmpty()) {
            throw new IllegalArgumentException("CUSTOMER ID CANNOT BE NULL OR EMPTY");
        }

        List<Items> products = redisTemplate.opsForList().range(customerId, 0, -1);
        if (products == null || products.isEmpty()) {
            log.warn("NOT PRODUCTS FOUND WITH ID: {}", customerId);
            return List.of();
        }

        log.info("PRODUCTS + ID {}: {}", customerId, products);
        return products;
    }

    public void clearCart(String customerId) {
        redisTemplate.delete(customerId);
        log.info("CLEARED CART WITH ID {}", customerId);
    }
}