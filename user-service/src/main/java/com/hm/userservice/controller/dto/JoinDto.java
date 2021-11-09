package com.hm.userservice.controller.dto;


import com.hm.userservice.domain.constants.CompanyPosition;
import com.hm.userservice.domain.constants.Department;
import lombok.Data;

@Data
public class JoinDto {

    private String loginId;
    private String password;

    private String name;
    private Department department;

    private String email;

    private String phoneNumber;

    private CompanyPosition companyPosition;

}
