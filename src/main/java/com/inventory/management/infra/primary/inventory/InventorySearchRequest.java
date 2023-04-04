package com.inventory.management.infra.primary.inventory;

import com.inventory.management.domain.warehouse.City;
import com.inventory.management.domain.warehouse.Region;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class InventorySearchRequest {
    private final Long warehouseId;
    private final Region region;
    private final City city;
    private final Long categoryId;
}
