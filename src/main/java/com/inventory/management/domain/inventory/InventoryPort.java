package com.inventory.management.domain.inventory;

import com.inventory.management.domain.product.Product;
import com.inventory.management.domain.warehouse.Warehouse;

import java.util.Optional;

public interface InventoryPort {
    Optional<Inventory> findByWarehouseAndProduct(Warehouse warehouse, Product product);

    Inventory save(Inventory inventory);

    Integer deleteByWarehouseAndProduct(Warehouse warehouse, Product product);
}
