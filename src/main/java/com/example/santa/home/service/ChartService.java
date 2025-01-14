package com.example.santa.home.service;

import com.example.santa.home.vo.CategoryInventoryVO;
import com.example.santa.home.vo.MonthlyInOutInvVO;
import com.example.santa.home.vo.MonthlyOrderVO;
import com.example.santa.home.vo.WarehouseInventoryVO;

import java.util.List;

public interface ChartService {

    List<MonthlyInOutInvVO> getMonthlyInOutInvList();

    List<WarehouseInventoryVO> getWarehouseInventoryList();

    List<CategoryInventoryVO> getCategoryInventoryList();

    List<MonthlyOrderVO> getMonthlyOrderList();

}
