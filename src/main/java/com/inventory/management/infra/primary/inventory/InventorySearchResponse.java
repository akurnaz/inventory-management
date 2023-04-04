package com.inventory.management.infra.primary.inventory;

import com.inventory.management.domain.inventory.Inventory;
import lombok.Getter;

@Getter
public class InventorySearchResponse {
    private final Long warehouseId;
    private final String warehouseName;
    private final Long productId;
    private final String productName;
    private final long quantity;

    public InventorySearchResponse(Inventory inventory) {
        this.warehouseId = inventory.getWarehouse().getId();
        this.warehouseName = inventory.getWarehouse().getName();
        this.productId = inventory.getProduct().getId();
        this.productName = inventory.getProduct().getName();
        this.quantity = inventory.getQuantity();
    }
}
