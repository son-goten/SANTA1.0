package com.example.santa.order.controller;

import com.example.santa.order.service.OrderService;
import com.example.santa.order.vo.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //주문 조회
    @GetMapping("read")
    public String status() {
        return "order/read";
    }

    //주문 조회 테이블
    @GetMapping("readOrder")
    @ResponseBody
    public List<OrderDTO> getOrdersList(){

        List<OrderDTO> list = orderService.selectAllOrders();
        return list;
    }

    //주문 일자 검색
    @PostMapping("searchByOrderDate")
    @ResponseBody
    public List<OrderDTO> searchByOrderDate(@RequestBody Map<String, String> dateRange) {
        String startDate = dateRange.get("startDate");
        String endDate = dateRange.get("endDate");

        System.out.println("받은 날짜: " + startDate + " ~ " + endDate);

        List<OrderDTO> list = orderService.searchByOrderDate(startDate, endDate);
        return list;
    }
    
    //상품명 검색
    @PostMapping("searchByProductName")
    @ResponseBody
    public List<OrderDTO> searchByProductName(@RequestBody OrderDTO orderDTO) {

        System.out.println("상품 검색 : " + orderDTO.getProductName());

        String productName = orderDTO.getProductName().trim();

        List<OrderDTO> list = orderService.searchByProductName(productName);
        return list;
    }

    //주문 지점 검색
    @PostMapping("searchByBranchName")
    @ResponseBody
    public List<OrderDTO> searchByBranchName(@RequestBody OrderDTO orderDTO) {

        System.out.println("지점 검색 : " + orderDTO.getBranchName());

        String branchName = orderDTO.getBranchName().trim();

        List<OrderDTO> list = orderService.searchByBranchName(branchName);
        return list;
    }

    //주문 상태 검색
    @PostMapping("searchByOrderStatus")
    @ResponseBody
    public List<OrderDTO> searchByOrderStatus(@RequestBody OrderDTO orderDTO) {

        System.out.println("주문 상태 : " + orderDTO.getOrderStatus());

        String orderStatus = orderDTO.getOrderStatus().trim();

        List<OrderDTO> list = orderService.searchByOrderStatus(orderStatus);
        return list;
    }


    //주문 승인
    @GetMapping("approval")
    public String details() {
        return "order/approval";
    }

    //주문 통계
    @GetMapping("statistics")
    public String statistics() {
        return "order/statistics";
    }
}
