package com.example.santa.createAccount.mapper;

import com.example.santa.createAccount.vo.AdministratorVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdministratorMapper {
    void insertAdministrator(AdministratorVO administrator);
}

