package com.hm.userservice.service.find;

import com.hm.userservice.domain.User;
import com.hm.userservice.domain.UserRepo;
import com.hm.userservice.domain.constants.CompanyPosition;
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
        return repo.findById(id).orElseThrow();
    }

    public List<User> findAll(){
        return repo.findAll();
    }

    public Boolean CheckFindAuthority(String loginId){
        Optional<User> optionalUser = repo.findUserByLoginId(loginId);
        if(optionalUser.isEmpty() || optionalUser.get().getCompanyPosition()==null)
            return false;
        if(optionalUser.get().getCompanyPosition() != CompanyPosition.인턴)
            return true;
        return false;
    }

}
