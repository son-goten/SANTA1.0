package com.example.santa.outgoing.mapper;

import com.example.santa.outgoing.vo.OutgoingDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OutgoingMapper {
    // 1. 전체 출고 조회
    List<OutgoingDTO> findAllOutgoing();

    // 2. 지점별 출고 조회
    List<OutgoingDTO> findOutgoingByBranchName(@Param("branchName") String branchName);

    // 3. 카테고리별 출고 조회
    List<OutgoingDTO> findOutgoingByCategory(@Param("category") String category);

    // 4. 날짜별 출고 조회
    List<OutgoingDTO> findOutgoingByDate(@Param("startDate") String startDate,
                                         @Param("endDate") String endDate);
}
