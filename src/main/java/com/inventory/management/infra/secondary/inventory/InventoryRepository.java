package com.inventory.management.infra.secondary.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    Optional<InventoryEntity> findByWarehouseIdAndProductId(Long warehouseId, Long productId);

    Integer deleteByWarehouseIdAndProductId(Long warehouseId, Long productId);
}
