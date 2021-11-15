package com.hm.userservice.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter @Getter
public class LoginDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

}
