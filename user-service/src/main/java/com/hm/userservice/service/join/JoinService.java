package com.hm.userservice.service.join;

import com.hm.userservice.controller.dto.EditDto;
import com.hm.userservice.controller.dto.JoinDto;
import com.hm.userservice.controller.dto.LoginDto;
import com.hm.userservice.domain.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface JoinService {

    User join(JoinDto joinDto);

    void login(LoginDto loginDto);

    default void logout() {}

    default User edit(EditDto editDto) {
        return null;
    }

}
