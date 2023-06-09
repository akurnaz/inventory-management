package com.inventory.management.infra.secondary.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {

}
