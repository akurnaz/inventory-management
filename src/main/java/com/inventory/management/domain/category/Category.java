package com.inventory.management.domain.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Category {
    private final Long id;
    private final String name;
}
