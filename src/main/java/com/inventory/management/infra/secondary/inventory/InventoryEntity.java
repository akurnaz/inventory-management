package com.inventory.management.infra.secondary.inventory;


import com.inventory.management.domain.inventory.Inventory;
import com.inventory.management.infra.secondary.jpa.AbstractAuditingEntity;
import com.inventory.management.infra.secondary.product.ProductEntity;
import com.inventory.management.infra.secondary.warehouse.WarehouseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "Inventory")
@Table(name = "inventory")
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEntity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private WarehouseEntity warehouse;

    @ManyToOne
    @NotNull
    private ProductEntity product;

    @NotNull
    @Min(0)
    private Long quantity;

    public static InventoryEntity fromModel(Inventory inventory) {
        return new InventoryEntity(
                inventory.getId(),
                WarehouseEntity.fromModel(inventory.getWarehouse()),
                ProductEntity.fromModel(inventory.getProduct()),
                inventory.getQuantity()
        );
    }

    public Inventory toModel() {
        return new Inventory(
                id,
                warehouse.toModel(),
                product.toModel(),
                quantity
        );
    }
}
