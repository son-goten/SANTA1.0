package com.example.santa.outgoing.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
 //화면에 넘겨줄 때 json 날짜 데이터 포맷팅 해줌
public class OutgoingDTO {

   private int outgoingId;
    private int orderId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
   private LocalDateTime outgoingDate;

    public String getFormattedOutgoingDate() {
        if (outgoingDate != null) {
            return outgoingDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        return "";
    }

    private String outgoingStatus;
    private String warehouseName;
    private String branchName;
    private String productCategory;
    private String productName;
    private int outgoingQuantity;
    private long productPrice;
    private long totalPrice;

    public String getFormattedProductPrice() {
        return formatCurrency(productPrice);
    }

    public String getFormattedTotalPrice() {
        return formatCurrency(totalPrice);
    }

    // 공통 포맷팅 메서드
    private String formatCurrency(long amount) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
        return numberFormat.format(amount) + "원";
    }
}
