package com.inventory.management.infra.secondary.inventory;

import com.inventory.management.domain.inventory.Inventory;
import com.inventory.management.domain.inventory.InventoryPort;
import com.inventory.management.domain.product.Product;
import com.inventory.management.domain.warehouse.Warehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InventoryAdapter implements InventoryPort {
    private final InventoryRepository inventoryRepository;

    @Override
    public Optional<Inventory> findByWarehouseAndProduct(Warehouse warehouse, Product product) {
        return inventoryRepository.findByWarehouseIdAndProductId(warehouse.getId(), product.getId())
                .map(InventoryEntity::toModel);
    }

    @Override
    public Inventory save(Inventory inventory) {
        InventoryEntity inventoryEntity = InventoryEntity.fromModel(inventory);
        inventoryEntity = inventoryRepository.save(inventoryEntity);
        return inventoryEntity.toModel();
    }

    @Override
    public Integer deleteByWarehouseAndProduct(Warehouse warehouse, Product product) {
        return inventoryRepository.deleteByWarehouseIdAndProductId(warehouse.getId(), product.getId());
    }
}
