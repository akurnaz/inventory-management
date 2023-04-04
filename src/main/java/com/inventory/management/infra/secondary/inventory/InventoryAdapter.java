package com.inventory.management.infra.secondary.inventory;

import com.inventory.management.domain.inventory.Inventory;
import com.inventory.management.domain.inventory.InventoryPort;
import com.inventory.management.domain.product.Product;
import com.inventory.management.domain.warehouse.Warehouse;
import com.inventory.management.infra.primary.inventory.InventorySearchRequest;
import com.inventory.management.infra.secondary.category.CategoryEntity;
import com.inventory.management.infra.secondary.product.ProductEntity;
import com.inventory.management.infra.secondary.warehouse.WarehouseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public List<Inventory> search(InventorySearchRequest searchRequest) {
        CategoryEntity categoryEntity = new CategoryEntity(searchRequest.getCategoryId(), null);
        ProductEntity productEntity = new ProductEntity(null, categoryEntity, null, null);
        WarehouseEntity warehouseEntity = new WarehouseEntity(searchRequest.getWarehouseId(), null, null, searchRequest.getRegion(), searchRequest.getCity());
        InventoryEntity inventoryEntity = new InventoryEntity(null, warehouseEntity, productEntity, null);

        return inventoryRepository.findAll(Example.of(inventoryEntity)).stream()
                .map(InventoryEntity::toModel)
                .toList();
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
