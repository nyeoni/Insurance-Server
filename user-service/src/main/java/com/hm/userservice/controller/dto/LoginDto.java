package com.hm.userservice.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {

    @NotBlank(message = "Login Id는 공백일 수 없습니다.")
    private String loginId;

    @NotBlank(message = "Password는 공백일 수 없습니다.")
    private String password;
}
