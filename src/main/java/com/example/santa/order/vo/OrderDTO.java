package com.example.santa.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //날짜 포맷팅
    private LocalDateTime orderDate;
    private int orderId;
    private String orderStatus;
    private String branchName;
    private String warehouseName;
    private String productName;
    private int inventoryQuantity;
    private int quantity;
    private double price;
    private double totalPrice;
}
