package org.ecommerce.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.product.dto.ProductRequestDTO;
import org.ecommerce.product.dto.mapper.ProductMapper;
import org.ecommerce.product.exception.ProductQuantityException;
import org.ecommerce.product.exception.ProductSearchException;
import org.ecommerce.product.exception.ProductStatusException;
import org.ecommerce.product.model.Product;
import org.ecommerce.product.dto.ProductPurchaseRequest;
import org.ecommerce.product.dto.ProductPurchaseResponse;
import org.ecommerce.product.model.ProductStatus;
import org.ecommerce.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Long addProduct(ProductRequestDTO request) {
        return repository.save(mapper.toProduct(request)).getProductId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requestedProducts) {
        log.info("PURCHASING PRODUCTS FROM ORDER_SERVICE IN PRODUCT_SERVICE, BODY :: <{}>",requestedProducts);
        var ids = requestedProducts.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        var sortedFoundProductsById = repository.findProductsByProductIdInOrderByProductId(ids);
        // -------------------------------------------------------------------------- VALIDATIONS
        if (ids.size() != sortedFoundProductsById.size()) {
            throw new ProductSearchException("ONE OR MORE PRODUCTS DO NOT EXIST/ARE OUT OF ORDER");
        }

        Set<Long> idSet = new HashSet<>(ids);
        int index = 0;

        for (Product product : sortedFoundProductsById) {
            if (!idSet.contains(product.getProductId())) {
                throw new ProductSearchException("PRODUCT ID " + product.getProductId() + " DOES NOT MATCH PRODUCT ID IN DATABASE.");
            }
            if (product.getStatus()==ProductStatus.INACTIVE) {
                throw new ProductStatusException("PRODUCT INACTIVE WITH ID :: "+product.getProductId());
            }
            int quantity = requestedProducts.get(index++).requestedQuantity();
            if (quantity > product.getStockQuantity() || quantity<=0) {
                throw new ProductQuantityException("REQUESTED QUANTITY CANNOT BE ABOVE STOCK QUANTITY OR BELOW ZERO");
            }
        }
        // -------------------------------------------------------------------------- VALIDATIONS
        var productPurchaseResponseList = new ArrayList<ProductPurchaseResponse>();

        index = 0;
        for (Product product : sortedFoundProductsById) {
            var requestQuantity = requestedProducts.get(index++).requestedQuantity();
            product.setStockQuantity(product.getStockQuantity()-requestQuantity);
            productPurchaseResponseList.add(mapper.toProductPurchaseResponse(product, requestQuantity));
        }
        repository.saveAll(sortedFoundProductsById);
        return productPurchaseResponseList;
    }
}
