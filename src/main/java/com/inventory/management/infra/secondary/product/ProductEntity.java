package com.inventory.management.infra.secondary.product;

import com.inventory.management.domain.product.Product;
import com.inventory.management.infra.secondary.category.CategoryEntity;
import com.inventory.management.infra.secondary.jpa.AbstractAuditingEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "Product")
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    @NotNull
    private CategoryEntity category;

    @NotBlank
    @Size(max = 256)
    private String name;

    @NotNull
    @Min(0)
    private Long threshold;

    public static ProductEntity fromModel(Product product) {
        return new ProductEntity(
                product.getId(),
                CategoryEntity.fromModel(product.getCategory()),
                product.getName(),
                product.getThreshold()
        );
    }

    public Product toModel() {
        return new Product(
                id,
                category.toModel(),
                name,
                threshold
        );
    }
}
