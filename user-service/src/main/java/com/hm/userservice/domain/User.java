package com.hm.userservice.domain;

import com.hm.userservice.domain.constants.CompanyPosition;
import com.hm.userservice.domain.constants.Department;
import com.hm.userservice.global.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Entity
public class User extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userId;

    @Size(min = 4, max = 20)
    @Column(nullable = false, unique = true)
    private String loginId;

    @Size(min = 4, max = 20)
    @Column(nullable = false)
    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private CompanyPosition companyPosition;

    @Email
    private String email;

    private String phoneNumber;

}
