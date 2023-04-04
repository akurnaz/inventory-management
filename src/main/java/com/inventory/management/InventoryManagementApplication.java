package com.inventory.management;

import com.inventory.management.infra.secondary.category.CategoryEntity;
import com.inventory.management.infra.secondary.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class InventoryManagementApplication implements ApplicationRunner {
	private final CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		CategoryEntity artsCategoryEntity = new CategoryEntity(null, "Arts");
		CategoryEntity gamesCategoryEntity = new CategoryEntity(null, "Games");
		CategoryEntity kidsAndTeensCategoryEntity = new CategoryEntity(null, "Kids $ Teens");
		CategoryEntity healthCategoryEntity = new CategoryEntity(null, "Health");

		categoryRepository.saveAll(List.of(artsCategoryEntity, gamesCategoryEntity, kidsAndTeensCategoryEntity, healthCategoryEntity));

	}
}
