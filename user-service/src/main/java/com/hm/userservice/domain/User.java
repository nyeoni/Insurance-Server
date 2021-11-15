package com.hm.userservice.domain;

import com.hm.userservice.domain.constants.CompanyPosition;
import com.hm.userservice.domain.constants.Department;
import com.hm.userservice.global.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter
@Entity
public class User extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userId;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private CompanyPosition companyPosition;

    private String email;

    private String phoneNumber;

}
