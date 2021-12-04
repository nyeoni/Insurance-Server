package com.hm.userservice.domain;

import com.hm.userservice.controller.dto.JoinDto;
import com.hm.userservice.domain.constants.CompanyPosition;
import com.hm.userservice.domain.constants.Department;
import com.hm.userservice.global.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
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


    public User modifyUser(JoinDto joinDto) {
        this.setLoginId(joinDto.getLoginId());
        this.setPassword(joinDto.getPassword());
        this.setName(joinDto.getName());
        this.setDepartment(joinDto.getDepartment());
        this.setCompanyPosition(joinDto.getCompanyPosition());
        this.setEmail(joinDto.getEmail());
        this.setPhoneNumber(joinDto.getPhoneNumber());
        return this;
    }
}
