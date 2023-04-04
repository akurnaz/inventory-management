package com.inventory.management.infra.secondary.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
