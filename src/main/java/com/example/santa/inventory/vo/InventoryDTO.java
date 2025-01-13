package com.example.santa.inventory.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
    private int productId;
    private String productName;
    private int capacity;
    private int inventoryQuantity; // 창고별 재고들을 합친 총 재고
    private String warehouseName;
    private int warehouseQuantity; // 창고별 재고

}
