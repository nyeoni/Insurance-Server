package com.hm.userservice.controller;

import com.hm.userservice.controller.dto.DetailUserDto;
import com.hm.userservice.domain.User;
import com.hm.userservice.global.EntityBody;
import com.hm.userservice.global.MessageSourceHandler;
import com.hm.userservice.service.find.FindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class FindController {

    private final FindService findService;
    private final MessageSourceHandler ms;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EntityBody<DetailUserDto>> findById(@PathVariable Long id){
        User findUser = findService.findById(id);
        String message = ms.getMessage("Find.user", id);
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(DetailUserDto.byUser(findUser),message));
    }

    @GetMapping
    public ResponseEntity<EntityBody<Stream<DetailUserDto>>> findAll(){
        Stream<DetailUserDto> userDetailDtoStream = findService.findAll().stream().map(user -> DetailUserDto.byUser(user));
        String message = ms.getMessage("Find.user", "전체");
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(userDetailDtoStream,message));
    }

}
