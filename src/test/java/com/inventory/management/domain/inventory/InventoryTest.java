package com.inventory.management.domain.inventory;

import com.inventory.management.domain.category.Category;
import com.inventory.management.domain.product.Product;
import com.inventory.management.domain.warehouse.City;
import com.inventory.management.domain.warehouse.Region;
import com.inventory.management.domain.warehouse.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Category category;
    private Warehouse warehouse;
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        category = new Category(1L, "Category 1");
        warehouse = new Warehouse(1L, "Warehouse 1", "Address 1", Region.IC_ANADOLU, City.ESKISEHIR);
        Product product = new Product(1L, category, "Description 1", 10L);
        inventory = new Inventory(1L, warehouse, product, 5L);
    }

    @Test
    public void testAddProduct() {
        inventory.addProduct(10L);
        assertEquals(15L, inventory.getQuantity());
    }

    @Test
    public void testAddProductWithInvalidQuantity() {
        try {
            inventory.addProduct(-5L);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity to add must be greater than zero", e.getMessage());
        }
    }

    @Test
    public void testSubtractProduct() {
        inventory.subtractProduct(2L);
        assertEquals(3L, inventory.getQuantity());
    }

    @Test
    public void testSubtractProductWithInvalidQuantity() {
        try {
            inventory.subtractProduct(10L);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity to subtract must be less or equal than current quantity", e.getMessage());
        }
    }

    @Test
    public void testSetQuantity() {
        inventory.setQuantity(20L);
        assertEquals(20L, inventory.getQuantity());
    }

    @Test
    public void testSetQuantityWithInvalidQuantity() {
        try {
            inventory.setQuantity(-5L);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity to edit must be greater or equal than zero", e.getMessage());
        }
    }

    @Test
    public void testIsQuantityLessThanThreshold() {
        Product product = new Product(1L, category, "Product 1", 10L);
        Inventory inventory = new Inventory(1L, warehouse, product, 15L);
        assertFalse(inventory.isQuantityLessThanThreshold());

        product = new Product(1L, category, "Product 1", 10L);
        inventory = new Inventory(1L, warehouse, product, 5L);
        assertTrue(inventory.isQuantityLessThanThreshold());
    }
}
