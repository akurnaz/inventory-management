package com.inventory.management.domain.mail;

import com.inventory.management.domain.inventory.Inventory;
import com.inventory.management.domain.product.Product;
import com.inventory.management.domain.warehouse.Warehouse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailService {

    public void sendThresholdAlertMail(Inventory inventory) {
        Warehouse warehouse = inventory.getWarehouse();
        Product product = inventory.getProduct();

        log.info("Current quantity({}) of product({}) at warehouse({}) is less than threshold({})",
                inventory.getQuantity(), product.getName(), warehouse.getName(), product.getThreshold());
    }
}
