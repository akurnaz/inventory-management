package com.inventory.management.infra.secondary.warehouse;

import com.inventory.management.domain.warehouse.City;
import com.inventory.management.domain.warehouse.Region;
import com.inventory.management.infra.secondary.jpa.AbstractAuditingEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;


@Entity(name = "Warehouse")
@Table(name = "warehouse")
@RequiredArgsConstructor
public class WarehouseEntity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @NotBlank
    @Size(max = 256)
    private final String name;

    @NotBlank
    @Size(max = 256)
    private final String address;

    @Enumerated(EnumType.STRING)
    @NotNull
    private final Region region;

    @Enumerated(EnumType.STRING)
    @NotNull
    private final City city;
}
