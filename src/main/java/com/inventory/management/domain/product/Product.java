package com.inventory.management.domain.product;

import com.inventory.management.domain.category.Category;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Product {
    private final Long id;
    private final Category category;
    private final String name;
    private final long threshold;
}
