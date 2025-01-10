package com.example.santa.find.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface FindMapper {
    String findIdByEmail(String email);

    void updatePasswordByEmail(Map<String, Object> params);
}
