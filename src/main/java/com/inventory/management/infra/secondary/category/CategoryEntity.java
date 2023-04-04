package com.inventory.management.infra.secondary.category;

import com.inventory.management.infra.secondary.jpa.AbstractAuditingEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

@Entity(name = "Category")
@Table(name = "category")
@RequiredArgsConstructor
public class CategoryEntity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @NotBlank
    @Size(max = 256)
    private final String name;

}
