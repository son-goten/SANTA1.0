package com.example.santa.home.mapper;

import com.example.santa.home.vo.CategoryInventoryVO;
import com.example.santa.home.vo.MonthlyInOutInvVO;
import com.example.santa.home.vo.MonthlyOrderVO;
import com.example.santa.home.vo.WarehouseInventoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChartMapper {

    // (1) & (5) 월별 입/출/재고량
    List<MonthlyInOutInvVO> getMonthlyInOutInvList();

    // (2) 창고별 재고량
    List<WarehouseInventoryVO> getWarehouseInventoryList();

    // (3) 카테고리별 재고량
    List<CategoryInventoryVO> getCategoryInventoryList();

    // (4) 월별 주문량
    List<MonthlyOrderVO> getMonthlyOrderList();
}
