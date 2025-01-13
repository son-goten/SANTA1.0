package com.example.santa.order.service;


import com.example.santa.order.mapper.OrderMapper;
import com.example.santa.order.vo.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    //*************************************************
    //******************** 주문 조회 ********************
    //*************************************************
    //주문 조회 list
    public List<OrderDTO> readOrder(){
        return orderMapper.readOrder();
    }

    //주문 일자 검색
    public List<OrderDTO> searchByOrderDate(String startDate, String endDate) {
        if (startDate == null || endDate == null || startDate.isEmpty() || endDate.isEmpty()) {
            throw new IllegalArgumentException("시작일자와 종료일자를 입력해야 합니다.");
        }

        if (!isValidDate(startDate) || !isValidDate(endDate)) {
            throw new IllegalArgumentException("잘못된 날짜 형식입니다.");
        }

        return orderMapper.searchByOrderDate(startDate, endDate);
    }

    //상품 검색
    public List<OrderDTO> searchByProductName(String productName) {
        return orderMapper.searchByProductName(productName);
    }

    //주문 지점 검색
    public List<OrderDTO> searchByBranchName(String branchName) {
        return orderMapper.searchByBranchName(branchName);
    }

    //주문 상태 검색
    public List<OrderDTO> searchByOrderStatus(String orderStatus) {
        return orderMapper.searchByOrderStatus(orderStatus);
    }

    //*************************************************
    //******************** 주문 승인 ********************
    //*************************************************
    //승인 대기 주문 list
    public List<OrderDTO> readPendingOrders(){
        return orderMapper.readPendingOrders();
    }

    //주문 승인/거절
    public int updateOrderStatus(int orderId, String orderStatus) {
        //orderIdFind() 해서 재고량과 주문량 비교해서 처리한 값 넘겨주기

        return orderMapper.updateOrderStatus(orderId,orderStatus);
    }

    // 승인 대기 주문, 주문 일자 검색
    public List<OrderDTO> searchByPendingOrderDate(String startDate, String endDate) {
        if (startDate == null || endDate == null || startDate.isEmpty() || endDate.isEmpty()) {
            throw new IllegalArgumentException("시작일자와 종료일자를 입력해야 합니다.");
        }

        if (!isValidDate(startDate) || !isValidDate(endDate)) {
            throw new IllegalArgumentException("잘못된 날짜 형식입니다.");
        }

        return orderMapper.searchByPendingOrderDate(startDate, endDate);
    }

    // 승인 대기 주문, 주문 상품 검색
    public List<OrderDTO> searchByPendingProductName(String productName) {
        return orderMapper.searchByPendingProductName(productName);
    }

    // 승인 대기 주문, 주문 지점 검색
    public List<OrderDTO> searchByPendingBranchName(String branchName) {
        return orderMapper.searchByPendingBranchName(branchName);
    }
    
    //*************************************************
    //******************** 로직 메서드 ******************
    //*************************************************
    // 날짜 검증 메서드 추가
    private boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    //*************************************************
    //******************** 주문 통계 ********************
    //*************************************************
}
