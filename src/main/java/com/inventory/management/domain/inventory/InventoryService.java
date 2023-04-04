package com.inventory.management.domain.inventory;

import com.inventory.management.domain.mail.MailService;
import com.inventory.management.domain.product.Product;
import com.inventory.management.domain.warehouse.Warehouse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryPort inventoryPort;
    private final MailService mailService;

    @Transactional
    public Inventory add(AddInventory addInventory) {
        Inventory inventory = inventoryPort.findByWarehouseAndProduct(addInventory.getWarehouse(), addInventory.getProduct())
                .orElseGet(() -> Inventory.empty(addInventory.getWarehouse(), addInventory.getProduct()));

        inventory.addProduct(addInventory.getQuantityToBeAdded());

        return inventoryPort.save(inventory);
    }

    @Transactional
    public Inventory subtract(SubtractInventory subtractInventory) {
        Inventory inventory = inventoryPort.findByWarehouseAndProduct(subtractInventory.getWarehouse(), subtractInventory.getProduct())
                .orElseThrow(() -> new NoSuchElementException("Inventory not found"));

        inventory.subtractProduct(subtractInventory.getQuantityToBeSubtracted());

        if (inventory.isQuantityLessThanThreshold()) {
            mailService.sendThresholdAlertMail(inventory);
        }

        return inventoryPort.save(inventory);
    }

    @Transactional
    public Inventory edit(EditInventory editInventory) {
        Inventory inventory = inventoryPort.findByWarehouseAndProduct(editInventory.getWarehouse(), editInventory.getProduct())
                .orElseThrow(() -> new NoSuchElementException("Inventory not found"));

        inventory.setQuantity(editInventory.getQuantityToBeEdited());

        return inventoryPort.save(inventory);
    }

    @Transactional
    public Integer delete(DeleteInventory deleteInventory) {
        return inventoryPort.deleteByWarehouseAndProduct(deleteInventory.getWarehouse(), deleteInventory.getProduct());
    }


    @RequiredArgsConstructor
    @Getter
    public static class AddInventory {
        private final Warehouse warehouse;
        private final Product product;
        private final long quantityToBeAdded;
    }

    @RequiredArgsConstructor
    @Getter
    public static class SubtractInventory {
        private final Warehouse warehouse;
        private final Product product;
        private final long quantityToBeSubtracted;
    }

    @RequiredArgsConstructor
    @Getter
    public static class EditInventory {
        private final Warehouse warehouse;
        private final Product product;
        private final long quantityToBeEdited;
    }

    @RequiredArgsConstructor
    @Getter
    public static class DeleteInventory {
        private final Warehouse warehouse;
        private final Product product;
    }
}
