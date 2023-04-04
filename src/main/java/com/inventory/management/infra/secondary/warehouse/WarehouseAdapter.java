package com.inventory.management.infra.secondary.warehouse;

import com.inventory.management.domain.warehouse.Warehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WarehouseAdapter {
    private final WarehouseRepository warehouseRepository;

    public Warehouse save(Warehouse warehouse) {
        WarehouseEntity warehouseEntity = WarehouseEntity.fromModel(warehouse);
        warehouseEntity = warehouseRepository.save(warehouseEntity);
        return warehouseEntity.toModel();
    }
}
