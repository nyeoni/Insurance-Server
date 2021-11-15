package com.hm.userservice.controller.dto;


import com.hm.userservice.domain.User;
import com.hm.userservice.domain.constants.CompanyPosition;
import com.hm.userservice.domain.constants.Department;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class JoinDto {

    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
    @NotBlank
    private String name;

    private Department department;

    private String email;

    private String phoneNumber;

    private CompanyPosition companyPosition;


    public User toUser() {
        return User.builder()
                .loginId(this.getLoginId())
                .password(this.getPassword())
                .name(this.getName())
                .department(this.getDepartment())
                .email(this.getEmail())
                .phoneNumber(this.getPhoneNumber())
                .companyPosition(this.getCompanyPosition())
                .build();
    }
}
