package com.example.santa.inventory.controller;

import com.example.santa.inventory.service.InventoryService;
import com.example.santa.inventory.vo.InventoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    //*************************************************
    //******************** 창고별 재고 조회 **************
    //*************************************************

    //창고별 재고 조회
    @GetMapping("warehouseRead")
    public String warehouseManagement() {

        return "inventory/warehouseRead";
    }

    //창고별 재고 list
    @GetMapping("readInventory")
    public List<InventoryDTO> readInventory() {

        return null;
    }

    //상품명 검색
    @PostMapping("searchByProductName")
    @ResponseBody
    public List<InventoryDTO> searchByProductName(String productName) {

        return null;
    }
    
    //창고 row 추가
    @PostMapping("addWarehouse")
    @ResponseBody
    public List<InventoryDTO> addWarehouse(String warehouseName) {

        return null;
    }
    
    //*************************************************
    //******************** 품목별 재고 조회 **************
    //*************************************************

    //품목별 재고 조회
    @GetMapping("categoryRead")
    public String categoryManagement() {

        return "inventory/categoryRead";
    }

    //품목별 재고 list
    @GetMapping("readProductInventory")
    @ResponseBody
    public List<InventoryDTO> readProductInventory() {
        List<InventoryDTO> list = inventoryService.readProductInventory();
        System.out.println("========= 품목별 재고 list =========");
        System.out.println(list);

        return list;
    }

}
