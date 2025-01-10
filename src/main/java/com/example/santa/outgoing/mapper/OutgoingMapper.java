package com.example.santa.outgoing.mapper;

import com.example.santa.outgoing.vo.OutgoingDetailsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OutgoingMapper {
    // 1. 전체 출고 조회
    List<OutgoingDetailsDTO> findAllOutgoing();

    // 2. 지점별 출고 조회
    List<OutgoingDetailsDTO> findOutgoingByBranchName(@Param("branchName") String branchName);

    // 3. 카테고리별 출고 조회
    List<OutgoingDetailsDTO> findOutgoingByCategory(@Param("category") String category);

    // 4. 날짜별 출고 조회
    List<OutgoingDetailsDTO> findOutgoingByDate(@Param("startDate") String startDate,
                                                @Param("endDate") String endDate);
}
