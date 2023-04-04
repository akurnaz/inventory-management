package com.inventory.management.infra.secondary.category;

import com.inventory.management.domain.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryAdapter {
    private final CategoryRepository categoryRepository;

    public Category save(Category category) {
        CategoryEntity categoryEntity = CategoryEntity.fromModel(category);
        categoryEntity = categoryRepository.save(categoryEntity);
        return categoryEntity.toModel();
    }
}
