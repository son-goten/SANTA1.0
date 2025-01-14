package com.example.santa.transit.mapper;

import com.example.santa.transit.vo.TransitDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TransitMapper {
    // 1. 전체 배송 조회
    List<TransitDTO> findAllTransit();

}
