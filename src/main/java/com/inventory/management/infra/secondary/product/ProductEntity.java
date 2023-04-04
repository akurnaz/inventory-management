package com.inventory.management.infra.secondary.product;

import com.inventory.management.infra.secondary.category.CategoryEntity;
import com.inventory.management.infra.secondary.jpa.AbstractAuditingEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

@Entity(name = "Product")
@Table(name = "product")
@RequiredArgsConstructor
public class ProductEntity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @ManyToOne
    @JoinColumn
    @NotNull
    private final CategoryEntity category;

    @NotBlank
    @Size(max = 256)
    private final String name;

    @NotNull
    @Min(0)
    private final Long quantity;

    @NotNull
    @Min(0)
    private final Long threshold;
}
