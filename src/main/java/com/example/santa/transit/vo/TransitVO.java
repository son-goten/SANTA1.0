package com.example.santa.transit.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransitVO {
    private int transitId;
    private int outgoingId;
    private int warehouseId;
    private int branchId;
    private int productId;
    private int transitQuantity;
    private String transitDate;
    private String transitStatus;

}
