package com.example.santa.outgoing.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class OutgoingDetailsDTO {
 private int outgoingId;
    private int orderId;
    private String outgoingDate;
    private String outgoingStatus;
    private String warehouseName;
    private String branchName;
    private String productCategory;
    private String productName;
    private int outgoingQuantity;
    private long productPrice;
    private long totalPrice;
}
