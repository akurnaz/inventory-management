package com.inventory.management.infra.secondary.category;

import com.inventory.management.domain.category.Category;
import com.inventory.management.infra.secondary.jpa.AbstractAuditingEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "Category")
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 256)
    private String name;

    public static CategoryEntity fromModel(Category category) {
        return new CategoryEntity(
                category.getId(),
                category.getName()
        );
    }

    public Category toModel() {
        return new Category(
                id,
                name
        );
    }
}
