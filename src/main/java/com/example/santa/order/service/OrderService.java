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

    public List<OrderDTO> selectAllOrders(){
        return orderMapper.selectAllOrders();
    }

    public List<OrderDTO> searchByOrderDate(String startDate, String endDate) {
        return orderMapper.searchByOrderDate(startDate, endDate);
    }

    public List<OrderDTO> searchByProductName(String productName) {
        return orderMapper.searchByProductName(productName);
    }

    public List<OrderDTO> searchByBranchName(String branchName) {
        return orderMapper.searchByBranchName(branchName);
    }

    public List<OrderDTO> searchByOrderStatus(String orderStatus) {
        return orderMapper.searchByOrderStatus(orderStatus);
    }
}
