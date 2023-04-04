package com.inventory.management.infra.secondary.product;

import com.inventory.management.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductAdapter {
    private final ProductRepository productRepository;

    public Product save(Product product) {
        ProductEntity productEntity = ProductEntity.fromModel(product);
        productEntity = productRepository.save(productEntity);
        return productEntity.toModel();
    }
}
