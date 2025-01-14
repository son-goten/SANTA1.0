package com.example.santa.home.controller;

import com.example.santa.home.service.ChartService;
import com.example.santa.home.vo.MonthlyInOutInvVO;
import com.example.santa.home.vo.MonthlyOrderVO;
import com.example.santa.home.vo.WarehouseInventoryVO;
import com.example.santa.home.vo.CategoryInventoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChartController {

    private final ChartService chartService;

    @GetMapping("/home")
    public String home(Model model) {
        // (1) DB에서 차트 데이터 조회
        List<MonthlyInOutInvVO> monthlyInOutInvList = chartService.getMonthlyInOutInvList();
        List<WarehouseInventoryVO> warehouseInventoryList = chartService.getWarehouseInventoryList();
        List<CategoryInventoryVO> categoryInventoryList = chartService.getCategoryInventoryList();
        List<MonthlyOrderVO> monthlyOrderList = chartService.getMonthlyOrderList();

        // (2) '월별 주문량'의 총합
        int totalOrders = 0;
        if (monthlyOrderList != null) {
            totalOrders = monthlyOrderList.stream()
                    .mapToInt(MonthlyOrderVO::getOrderCount)
                    .sum();
        }

        // (3) Model에 담기
        model.addAttribute("monthlyInOutInvList", monthlyInOutInvList);
        model.addAttribute("warehouseInventoryList", warehouseInventoryList);
        model.addAttribute("categoryInventoryList", categoryInventoryList);
        model.addAttribute("monthlyOrderList", monthlyOrderList);

        // 합계 계산 결과를 별도 변수에 담아 View로 전달
        model.addAttribute("totalOrders", totalOrders);

        // (4) home.html로 이동
        return "home/home";
    }
}

