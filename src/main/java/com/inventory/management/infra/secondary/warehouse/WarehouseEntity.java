package com.inventory.management.infra.secondary.warehouse;

import com.inventory.management.domain.warehouse.City;
import com.inventory.management.domain.warehouse.Region;
import com.inventory.management.domain.warehouse.Warehouse;
import com.inventory.management.infra.secondary.jpa.AbstractAuditingEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity(name = "Warehouse")
@Table(name = "warehouse")
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseEntity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 256)
    private String name;

    @NotBlank
    @Size(max = 256)
    private String address;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Region region;

    @Enumerated(EnumType.STRING)
    @NotNull
    private City city;

    public static WarehouseEntity fromModel(Warehouse warehouse) {
        return new WarehouseEntity(
                warehouse.getId(),
                warehouse.getName(),
                warehouse.getAddress(),
                warehouse.getRegion(),
                warehouse.getCity()
        );
    }

    public Warehouse toModel() {
        return new Warehouse(
                id,
                name,
                address,
                region,
                city
        );
    }
}
