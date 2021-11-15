package com.hm.userservice.controller.dto;

import com.hm.userservice.domain.User;
import com.hm.userservice.domain.constants.CompanyPosition;
import com.hm.userservice.domain.constants.Department;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
public class UserDetailDto {

    private Long id;
    private String loginId;
    private String password;

    private String name;
    private Department department;

    private String email;

    private String phoneNumber;

    private CompanyPosition companyPosition;

    public static UserDetailDto byUser(User user) {
        return UserDetailDto.builder()
                .id(user.getUserId())
                .loginId(user.getLoginId())
                .password(user.getPassword())
                .name(user.getName())
                .department(user.getDepartment())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .companyPosition(user.getCompanyPosition())
                .build();
    }
}
