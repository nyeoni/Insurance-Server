package com.hm.userservice.service.join;

import com.hm.userservice.controller.dto.EditDto;
import com.hm.userservice.controller.dto.JoinDto;
import com.hm.userservice.controller.dto.LoginDto;
import com.hm.userservice.domain.User;
import com.hm.userservice.domain.UserRepo;
import com.hm.userservice.global.Mapper;
import com.hm.userservice.global.exception.InvalidLoginDtoError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SessionJoinServiceImpl implements JoinService{

    private final UserRepo userRepo;

    @Override
    public User join(JoinDto joinDto) {
        User user = Mapper.getMapper().convertValue(joinDto, User.class);
        userRepo.save(user);
        log.info("LoginId: {}님 가입성공",user.getLoginId());
        return user;
    }

    @Override
    public void login(LoginDto loginDto) {
        userRepo.findUserByLoginIdAndPassword(loginDto.getLoginId(), loginDto.getPassword())
                .orElseThrow(() -> new InvalidLoginDtoError());
    }

}
