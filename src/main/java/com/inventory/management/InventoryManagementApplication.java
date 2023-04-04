package com.inventory.management;

import com.inventory.management.domain.category.Category;
import com.inventory.management.domain.inventory.InventoryService;
import com.inventory.management.domain.product.Product;
import com.inventory.management.domain.warehouse.City;
import com.inventory.management.domain.warehouse.Region;
import com.inventory.management.domain.warehouse.Warehouse;
import com.inventory.management.infra.secondary.category.CategoryAdapter;
import com.inventory.management.infra.secondary.product.ProductAdapter;
import com.inventory.management.infra.secondary.warehouse.WarehouseAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class InventoryManagementApplication implements ApplicationRunner {
    private final WarehouseAdapter warehouseAdapter;
    private final CategoryAdapter categoryAdapter;
    private final ProductAdapter productAdapter;
    private final InventoryService inventoryService;

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Warehouse mainWarehouse = warehouseAdapter.save(new Warehouse(null, "Main Warehouse", "Organize Sanayi Bölgesi", Region.IC_ANADOLU, City.ESKISEHIR));
        Warehouse secondWarehouse = warehouseAdapter.save(new Warehouse(null, "Second Warehouse", "Organize Sanayi Bölgesi", Region.MARMARA, City.ISTANBUL));

        Category artsCategory = categoryAdapter.save(new Category(null, "Arts"));
        Category gamesCategory = categoryAdapter.save(new Category(null, "Games"));
        Category kidsAndTeensCategory = categoryAdapter.save(new Category(null, "Kids & Teens"));
        Category healthCategory = categoryAdapter.save(new Category(null, "Health"));

        Product artistPaintProduct = productAdapter.save(new Product(null, artsCategory, "Artist Paint", 5L));
        Product canvasProduct = productAdapter.save(new Product(null, artsCategory, "Canvas", 10L));
        Product gamingHeadsetProduct = productAdapter.save(new Product(null, gamesCategory, "Gaming Headset", 20L));
        Product babyBodyOilProduct = productAdapter.save(new Product(null, kidsAndTeensCategory, "Baby Body Oil", 15L));

        inventoryService.add(new InventoryService.AddInventory(mainWarehouse, artistPaintProduct, 10));
        inventoryService.subtract(new InventoryService.SubtractInventory(mainWarehouse, artistPaintProduct, 6));
        inventoryService.edit(new InventoryService.EditInventory(mainWarehouse, artistPaintProduct, 5));
        inventoryService.delete(new InventoryService.DeleteInventory(mainWarehouse, artistPaintProduct));
    }
}
