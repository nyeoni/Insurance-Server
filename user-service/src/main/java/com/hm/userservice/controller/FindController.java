package com.hm.userservice.controller;

import com.hm.userservice.controller.dto.UserDetailDto;
import com.hm.userservice.global.MessageSourceHandler;
import com.hm.userservice.global.ResponseDto;
import com.hm.userservice.service.find.FindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FindController {

    private final FindService findService;
    private final MessageSourceHandler ms;

    @GetMapping(value = {"user","user/{id}"})
    public ResponseDto findById(@PathVariable(required = false) Long id){
        if(id==null){
            String message = ms.getMessage("find.user", "전체", null);
            log.info(ms.getMessage("find.user","전체"));
            List<UserDetailDto> list = findService.findAll().stream()
                    .map(user -> UserDetailDto.byUser(user)).collect(Collectors.toList());
            return ResponseDto.builder().ok().message(message).data(list).build();
        }
        String message = ms.getMessage("find.user", id, null);
        log.info(ms.getMessage("find.user",id));
        return new ResponseDto.builder().ok()
                .message(message).data(findService.findById(id)).build();
    }

}
