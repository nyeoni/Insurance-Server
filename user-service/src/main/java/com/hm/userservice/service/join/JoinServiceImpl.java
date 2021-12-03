package com.hm.userservice.service.join;

import com.hm.userservice.controller.dto.JoinDto;
import com.hm.userservice.controller.dto.LoginDto;
import com.hm.userservice.domain.User;
import com.hm.userservice.domain.UserRepo;
import com.hm.userservice.global.MessageSourceHandler;
import com.hm.userservice.global.exception.InvalidFindException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class JoinServiceImpl implements JoinService{

    private final UserRepo userRepo;

    @Override
    public User join(JoinDto joinDto) {
        User user = joinDto.toUser();
        userRepo.save(user);
        return user;
    }

    @Override
    public User login(LoginDto loginDto) {
        return userRepo.findUserByLoginIdAndPassword(loginDto.getLoginId(), loginDto.getPassword())
                .orElseThrow(() -> new InvalidFindException.ByLoginDto());
    }

}
