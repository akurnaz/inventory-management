package com.inventory.management.domain.warehouse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Warehouse {
    private final Long id;
    private final String name;
    private final String address;
    private final Region region;
    private final City city;
}
