package com.inventory.management.infra.primary.inventory;

import com.inventory.management.infra.secondary.inventory.InventoryAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(InventoryController.PATH)
@Slf4j
@RequiredArgsConstructor
public class InventoryController {

    public static final String PATH = "/api/inventories";

    private final InventoryAdapter inventoryAdapter;

    @GetMapping("/search")
    public List<InventorySearchResponse> search(InventorySearchRequest searchRequest) {
        log.debug("GET {}/search {}", PATH, searchRequest);

        return inventoryAdapter.search(searchRequest).stream()
                .map(InventorySearchResponse::new)
                .toList();
    }

}
