package com.example.santa.order.vo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {

    private int orderId;
    private int warehouseId;
    private int branchId;
    private Date orderDate;
    private String orderStatus;

}
