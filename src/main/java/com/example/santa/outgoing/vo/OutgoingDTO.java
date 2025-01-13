package com.example.santa.outgoing.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
 //화면에 넘겨줄 때 json 날짜 데이터 포맷팅 해줌
public class OutgoingDTO {

   private int outgoingId;
    private int orderId;

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime outgoingDate;


   private String outgoingStatus;
    private String warehouseName;
    private String branchName;
    private String productCategory;
    private String productName;
    private int outgoingQuantity;
    private long productPrice;
    private long totalPrice;
}
