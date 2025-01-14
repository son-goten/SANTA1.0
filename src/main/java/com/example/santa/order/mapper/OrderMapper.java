package com.example.santa.order.mapper;

import com.example.santa.order.vo.OrderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

    //*************************************************
    //******************** 주문 조회 ********************
    //*************************************************
    //주문 조회 list
    List<OrderDTO> readOrder();
    //주문일자 검색
    List<OrderDTO> searchByOrderDate(@Param("startDate") String startDate, @Param("endDate") String endDate);
    //상품명 검색
    List<OrderDTO> searchByProductName(@Param("productName") String productName);
    //주문 지점 검색
    List<OrderDTO> searchByBranchName(@Param("branchName") String branchName);
    //주문 상태 검색
    List<OrderDTO> searchByOrderStatus(@Param("orderStatus") String orderStatus);

    //*************************************************
    //******************** 주문 승인 ********************
    //*************************************************
    //승인 대기 주문 list
    List<OrderDTO> readPendingOrders();

    //주문 승인/거절
    int updateOrderStatus(int orderId, String orderStatus);

    //주문 승인 시 출고 테이블에 추가(출고 대기 상태로)
    int insertOutgoingPending(int orderId);

    // 승인 대기 주문, 주문 일자 검색
    List<OrderDTO> searchByPendingOrderDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    // 승인 대기 주문, 주문 상품 검색
    List<OrderDTO> searchByPendingProductName(@Param("productName") String productName);

    // 승인 대기 주문, 주문 지점 검색
    List<OrderDTO> searchByPendingBranchName(@Param("branchName") String branchName);

    //*************************************************
    //******************** 주문 통계 ********************
    //*************************************************

}
