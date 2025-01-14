package com.example.santa.inventory.mapper;

import com.example.santa.inventory.vo.InventoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InventoryMapper {

    //*************************************************
    //******************** 창고별 재고 조회 **************
    //*************************************************

    //창고별 재고 list

    //상품명 검색

    //창고 row 추가

    //*************************************************
    //******************** 품목별 재고 조회 **************
    //*************************************************

    //품목별 재고 list
    List<InventoryDTO> readProductInventory();

}
