package com.example.santa.order.service;


import com.example.santa.order.mapper.OrderMapper;
import com.example.santa.order.vo.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    //*************************************************
    //******************** 주문 조회 ********************
    //*************************************************
    //주문 조회 list
    public List<OrderDTO> selectAllOrders(){
        return orderMapper.selectAllOrders();
    }

    //주문 일자 검색
    public List<OrderDTO> searchByOrderDate(String startDate, String endDate) {
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
    public boolean updateOrderStatus(int orderId, String status) {
        return orderMapper.updateOrderStatus(orderId,status);
    }

    // 승인 대기 주문, 주문 일자 검색

    // 승인 대기 주문, 주문 상품 검색

    // 승인 대기 주문, 주문 지점 검색

    //*************************************************
    //******************** 주문 통계 ********************
    //*************************************************
}
