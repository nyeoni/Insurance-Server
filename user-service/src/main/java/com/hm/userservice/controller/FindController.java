package com.hm.userservice.controller;

import com.hm.userservice.controller.dto.UserDetailDto;
import com.hm.userservice.global.ResponseDto;
import com.hm.userservice.service.find.FindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "find")
@RestController
public class FindController {

    private final FindService findService;

    @GetMapping(value = {"","{id}"})
    public ResponseEntity findById(@PathVariable(required = false) Long id){
        if(id==null){
            log.info("User 전체 조회");
            List<UserDetailDto> list = findService.findAll().stream()
                    .map(user -> UserDetailDto.byUser(user)).collect(Collectors.toList());
            return ResponseEntity.ok(ResponseDto.builder()
                    .result("SUCCESS").message("User 전체 결과").data(list).build());
        }
        log.info("User ID:"+id+" 조회");
        return ResponseEntity.ok(ResponseDto.builder()
                .result("SUCCESS").message("USER "+id+"결과").data(findService.findById(id)).build());
    }

}
