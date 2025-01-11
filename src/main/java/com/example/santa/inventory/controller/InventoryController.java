package com.example.santa.inventory.controller;

import com.example.santa.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    //창고별 재고 관리
    @GetMapping("warehouseRead")
    public String warehouseManagement(Model model) {

        return "inventory/warehouseRead";
    }

    //품목별 재고 관리
    @GetMapping("categoryRead")
    public String categoryManagement(Model model) {

        return "inventory/categoryRead";
    }

    //재고 이력 관리
    @GetMapping("historyRead")
    public String historyManagement(Model model) {

        return "inventory/historyRead";
    }

    //창고 재고
    @GetMapping("statistics")
    public String statistics(Model model) {

        return "inventory/statistics";
    }


}
