package com.example.santa.order.controller;

import com.example.santa.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final OrderService orderService;

}
