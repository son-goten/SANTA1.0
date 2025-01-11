package com.example.santa.order.controller;

import com.example.santa.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
