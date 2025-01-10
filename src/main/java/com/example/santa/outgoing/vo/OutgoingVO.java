package com.example.santa.outgoing.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutgoingVO {
    private int outgoingId;
    private int warehouseId;
    private int productId;
    private int orderId;
    private int branchId;
    private int outgoingQuantity;
    private String outgoingDate;
    private String outgoingStatus;
}
