package com.example.santa.login.mapper;

import com.example.santa.login.vo.LoginVO;
import com.example.santa.login.vo.UserDetailsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {

    LoginVO getUserByUserId(@Param("userId") String userId);

    UserDetailsVO getUserDetailsByUserId(@Param("userId") String userId);
}