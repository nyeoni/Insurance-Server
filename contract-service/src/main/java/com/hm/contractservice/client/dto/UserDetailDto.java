package com.hm.contractservice.client.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDetailDto {

    private Long id;
    private String loginId;
    private String password;

    private String name;
    private Department department;

    private String email;

    private String phoneNumber;

    private CompanyPosition companyPosition;

    public enum  Department {
        개발,영업,UW,보상,관리;
    }

    public enum CompanyPosition {
        인턴,사원,대리,과장,차장,부장,상무,사장
    }



}
