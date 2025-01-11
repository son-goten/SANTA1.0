package com.example.santa.inventory.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseVO {
    private int warehouseId;
    private String warehouseName;
    private String location;
    private int capacity;
}
