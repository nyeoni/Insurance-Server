package com.hm.userservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findUserByLoginId(String loginId);

    Optional<User> findUserByLoginIdAndPassword(String loginId, String password);

}
