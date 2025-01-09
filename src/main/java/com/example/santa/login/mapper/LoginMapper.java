package com.example.santa.login.mapper;

import com.example.santa.login.vo.LoginVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    LoginVO getUserByUserId(@Param("userId") String userId);
}
