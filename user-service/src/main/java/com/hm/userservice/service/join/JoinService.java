package com.hm.userservice.service.join;

import com.hm.userservice.controller.dto.JoinDto;
import com.hm.userservice.controller.dto.LoginDto;
import com.hm.userservice.domain.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface JoinService {

    User join(JoinDto joinDto);

    User login(LoginDto loginDto);

    default void logout() {}

}
