package com.hm.userservice.service.find;

import com.hm.userservice.domain.User;
import com.hm.userservice.domain.UserRepo;
import com.hm.userservice.domain.constants.CompanyPosition;
import com.hm.userservice.global.exception.InvalidFindException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FindService {

    private final UserRepo repo;

    public User findById(Long id){
        return repo.findById(id).orElseThrow(() -> new InvalidFindException.ById());
    }

    public List<User> findAll(){
        return repo.findAll();
    }

}
