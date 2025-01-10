package com.example.santa.outgoing.service;

import com.example.santa.outgoing.mapper.OutgoingMapper;
import com.example.santa.outgoing.vo.OutgoingDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutgoingService {
    private final OutgoingMapper outgoingMapper;

    public List<OutgoingDetailsDTO> findAllOutgoing(){
        return outgoingMapper.findAllOutgoing();
    }

    // 2. 지점별 출고 조회
    public List<OutgoingDetailsDTO> findOutgoingByBranchName(@Param("branchName") String branchName){
        return outgoingMapper.findOutgoingByBranchName(branchName);
    }

    // 3. 카테고리별 출고 조회
    public List<OutgoingDetailsDTO> findOutgoingByCategory(@Param("category") String category){
        return outgoingMapper.findOutgoingByCategory(category);
    }

    // 4. 날짜별 출고 조회
    public List<OutgoingDetailsDTO> findOutgoingByDate(@Param("startDate") String startDate,
                                                       @Param("endDate") String endDate){
        return outgoingMapper.findOutgoingByDate(startDate,endDate);
    }
}
