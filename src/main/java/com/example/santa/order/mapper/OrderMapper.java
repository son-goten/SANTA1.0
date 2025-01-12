package com.example.santa.order.mapper;

import com.example.santa.order.vo.OrderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

    //주문 조회 테이블
    List<OrderDTO> selectAllOrders();
    List<OrderDTO> searchByOrderDate(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<OrderDTO> searchByProductName(@Param("productName") String productName);
    List<OrderDTO> searchByBranchName(@Param("branchName") String branchName);
    List<OrderDTO> searchByOrderStatus(@Param("orderStatus") String orderStatus);
}
