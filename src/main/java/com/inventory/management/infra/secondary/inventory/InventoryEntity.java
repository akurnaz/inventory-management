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
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity(name = "Inventory")
@Table(name = "inventory")
@Audited
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEntity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @NotNull
    private WarehouseEntity warehouse;

    @ManyToOne
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
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
