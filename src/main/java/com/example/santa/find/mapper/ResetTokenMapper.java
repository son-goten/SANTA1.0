package com.example.santa.find.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ResetTokenMapper {

    // 토큰 저장
    void insertResetToken(@Param("email") String email, @Param("token") String token);

    // 토큰 검색
    String findEmailByToken(@Param("token") String token);

    // 토큰 삭제
    void deleteResetToken(@Param("token") String token);
}
