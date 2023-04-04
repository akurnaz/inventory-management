package com.inventory.management.domain.inventory;

import com.inventory.management.domain.category.Category;
import com.inventory.management.domain.mail.MailService;
import com.inventory.management.domain.product.Product;
import com.inventory.management.domain.warehouse.City;
import com.inventory.management.domain.warehouse.Region;
import com.inventory.management.domain.warehouse.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class InventoryServiceTest {
    private InventoryPort inventoryPort;
    private MailService mailService;
    private InventoryService inventoryService;
    private Warehouse warehouse;
    private Product product;

    private static final long initialQuantity = 10;
    private static final long threshold = 5;
    private static final long quantityToBeAdded = 10;
    private static final long quantityToBeSubtracted = 3;
    private static final long quantityToBeEdited = 5;


    @BeforeEach
    void setUp() {
        inventoryPort = mock(InventoryPort.class);
        mailService = mock(MailService.class);
        inventoryService = new InventoryService(inventoryPort, mailService);

        Category category = new Category(1L, "Category 1");
        warehouse = new Warehouse(1L, "Warehouse 1", "Address 1", Region.IC_ANADOLU, City.ESKISEHIR);
        product = new Product(1L, category, "Description 1", threshold);
    }

    @Test
    @DisplayName("Add inventory successfully")
    void addInventory() {
        when(inventoryPort.findByWarehouseAndProduct(warehouse, product)).thenReturn(Optional.empty());
        when(inventoryPort.save(any(Inventory.class))).thenAnswer(i -> i.getArguments()[0]);

        InventoryService.AddInventory addInventory = new InventoryService.AddInventory(warehouse, product, quantityToBeAdded);
        Inventory result = inventoryService.add(addInventory);

        assertEquals(quantityToBeAdded, result.getQuantity());
        verify(inventoryPort).findByWarehouseAndProduct(warehouse, product);
        verify(inventoryPort).save(any(Inventory.class));
    }

    @Test
    @DisplayName("Add inventory to existing inventory")
    void addInventoryToExistingInventory() {
        Inventory inventory = new Inventory(null, warehouse, product, initialQuantity);
        when(inventoryPort.findByWarehouseAndProduct(warehouse, product)).thenReturn(Optional.of(inventory));
        when(inventoryPort.save(inventory)).thenReturn(inventory);

        InventoryService.AddInventory addInventory = new InventoryService.AddInventory(warehouse, product, quantityToBeAdded);
        Inventory result = inventoryService.add(addInventory);

        assertEquals(initialQuantity + quantityToBeAdded, result.getQuantity());
        verify(inventoryPort).findByWarehouseAndProduct(warehouse, product);
        verify(inventoryPort).save(inventory);
    }

    @Test
    @DisplayName("Subtract inventory successfully")
    void subtractInventory() {
        Inventory inventory = new Inventory(null, warehouse, product, initialQuantity);
        when(inventoryPort.findByWarehouseAndProduct(warehouse, product)).thenReturn(Optional.of(inventory));
        when(inventoryPort.save(inventory)).thenReturn(inventory);

        InventoryService.SubtractInventory subtractInventory = new InventoryService.SubtractInventory(warehouse, product, quantityToBeSubtracted);
        Inventory result = inventoryService.subtract(subtractInventory);

        assertEquals(initialQuantity - quantityToBeSubtracted, result.getQuantity());
        verify(inventoryPort).findByWarehouseAndProduct(warehouse, product);
        verify(inventoryPort).save(inventory);
        verify(mailService, never()).sendThresholdAlertMail(inventory);
    }

    @Test
    @DisplayName("Subtract inventory from existing inventory with quantity less than threshold")
    public void testSubtractWithThreshold() {
        long quantityToBeSubtracted = 6;

        Inventory inventory = new Inventory(null, warehouse, product, initialQuantity);
        when(inventoryPort.findByWarehouseAndProduct(warehouse, product)).thenReturn(Optional.of(inventory));
        when(inventoryPort.save(inventory)).thenReturn(inventory);

        InventoryService.SubtractInventory subtractInventory = new InventoryService.SubtractInventory(warehouse, product, quantityToBeSubtracted);
        Inventory result = inventoryService.subtract(subtractInventory);

        assertEquals(initialQuantity - quantityToBeSubtracted, result.getQuantity());
        verify(mailService, only()).sendThresholdAlertMail(inventory);
    }

    @Test
    @DisplayName("Subtract inventory from non-existing inventory")
    public void testSubtractNoSuchElementException() {
        when(inventoryPort.findByWarehouseAndProduct(warehouse, product)).thenReturn(Optional.empty());

        InventoryService.SubtractInventory subtractInventory = new InventoryService.SubtractInventory(warehouse, product, quantityToBeSubtracted);

        assertThrows(NoSuchElementException.class, () -> inventoryService.subtract(subtractInventory));
        verify(mailService, never()).sendThresholdAlertMail(any());
    }

    @Test
    @DisplayName("Edit existing inventory")
    public void testEdit() {
        Inventory inventory = new Inventory(null, warehouse, product, initialQuantity);

        when(inventoryPort.findByWarehouseAndProduct(warehouse, product)).thenReturn(Optional.of(inventory));
        when(inventoryPort.save(inventory)).thenReturn(inventory);

        InventoryService.EditInventory editInventory = new InventoryService.EditInventory(warehouse, product, quantityToBeEdited);
        Inventory result = inventoryService.edit(editInventory);

        assertEquals(quantityToBeEdited, result.getQuantity());
    }

    @Test
    @DisplayName("Edit non-existing inventory")
    public void testEditNoSuchElementException() {
        when(inventoryPort.findByWarehouseAndProduct(warehouse, product)).thenReturn(Optional.empty());

        InventoryService.EditInventory editInventory = new InventoryService.EditInventory(warehouse, product, quantityToBeEdited);

        assertThrows(NoSuchElementException.class, () -> inventoryService.edit(editInventory));
    }

    @Test
    public void testDelete() {
        InventoryService.DeleteInventory deleteInventory = new InventoryService.DeleteInventory(warehouse, product);

        when(inventoryPort.deleteByWarehouseAndProduct(warehouse, product)).thenReturn(1);

        Integer result = inventoryService.delete(deleteInventory);

        verify(inventoryPort).deleteByWarehouseAndProduct(warehouse, product);
        assertEquals(1, result);
    }
}