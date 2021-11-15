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
public class SessionJoinServiceImpl implements JoinService{

    private final UserRepo userRepo;
    private final MessageSourceHandler ms;

    @Override
    public User join(JoinDto joinDto) {
        User user = joinDto.toUser();
        userRepo.save(user);
        log.info(ms.getMessage("join.User.id",joinDto.getLoginId()));
        return user;
    }

    @Override
    public Boolean login(LoginDto loginDto) {
        userRepo.findUserByLoginIdAndPassword(loginDto.getLoginId(), loginDto.getPassword())
                .orElseThrow(() -> new InvalidFindException.InvalidLoginDtoException());
        return true;
    }

}
