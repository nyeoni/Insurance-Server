package com.hm.contractservice.client;

import com.hm.contractservice.client.dto.UserDetailDto;
import com.hm.contractservice.global.EntityBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("user-service")
public interface UserService {

    @RequestMapping(method = RequestMethod.GET, path = "/user/{id}")
    ResponseEntity<EntityBody<UserDetailDto>> findById(@PathVariable Long id);

}