package com.inventory.management;

import com.inventory.management.domain.warehouse.City;
import com.inventory.management.domain.warehouse.Region;
import com.inventory.management.infra.secondary.category.CategoryEntity;
import com.inventory.management.infra.secondary.category.CategoryRepository;
import com.inventory.management.infra.secondary.product.ProductEntity;
import com.inventory.management.infra.secondary.product.ProductRepository;
import com.inventory.management.infra.secondary.warehouse.WarehouseEntity;
import com.inventory.management.infra.secondary.warehouse.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class InventoryManagementApplication implements ApplicationRunner {
	private final WarehouseRepository warehouseRepository;
	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		WarehouseEntity mainWarehouse = new WarehouseEntity(null, "Main Warehouse", "Organize Sanayi Bölgesi", Region.IC_ANADOLU, City.ESKISEHIR);
		WarehouseEntity secondWarehouse = new WarehouseEntity(null, "Second Warehouse", "Organize Sanayi Bölgesi", Region.MARMARA, City.ISTANBUL);

		warehouseRepository.saveAll(List.of(mainWarehouse, secondWarehouse));

		CategoryEntity artsCategoryEntity = new CategoryEntity(null, "Arts");
		CategoryEntity gamesCategoryEntity = new CategoryEntity(null, "Games");
		CategoryEntity kidsAndTeensCategoryEntity = new CategoryEntity(null, "Kids & Teens");
		CategoryEntity healthCategoryEntity = new CategoryEntity(null, "Health");

		categoryRepository.saveAll(List.of(artsCategoryEntity, gamesCategoryEntity, kidsAndTeensCategoryEntity, healthCategoryEntity));

		ProductEntity artistPaintProductEntity = new ProductEntity(null, artsCategoryEntity, "Artist Paint", 0L, 10L);
		ProductEntity canvasProductEntity = new ProductEntity(null, artsCategoryEntity, "Canvas", 0L, 20L);
		ProductEntity gamingHeadsetProductEntity = new ProductEntity(null, gamesCategoryEntity, "Gaming Headset", 0L, 50L);
		ProductEntity babyBodyOilProductEntity = new ProductEntity(null, kidsAndTeensCategoryEntity, "Baby Body Oil", 0L, 100L);

		productRepository.saveAll(List.of(artistPaintProductEntity, canvasProductEntity, gamingHeadsetProductEntity, babyBodyOilProductEntity));
	}
}
