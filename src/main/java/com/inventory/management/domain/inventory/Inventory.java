package com.inventory.management.domain.inventory;

import com.inventory.management.domain.product.Product;
import com.inventory.management.domain.warehouse.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Inventory {
    private final Long id;
    private final Warehouse warehouse;
    private final Product product;
    private long quantity;

    public static Inventory empty(Warehouse warehouse, Product product) {
        return new Inventory(null, warehouse, product, 0L);
    }

    public void addProduct(long quantityToBeAdded) {
        if (quantityToBeAdded <= 0) {
            throw new IllegalArgumentException("Quantity to add must be greater than zero");
        }

        quantity += quantityToBeAdded;
    }

    public void subtractProduct(long quantityToBeSubtracted) {
        if (quantityToBeSubtracted > quantity) {
            throw new IllegalArgumentException("Quantity to subtract must be less or equal than current quantity");
        }

        quantity -= quantityToBeSubtracted;
    }

    public void setQuantity(long quantityToBeEdited) {
        if (quantityToBeEdited < 0) {
            throw new IllegalArgumentException("Quantity to edit must be greater or equal than zero");
        }

        quantity = quantityToBeEdited;
    }

    public boolean isQuantityLessThanThreshold() {
        return quantity < product.getThreshold();
    }


}
