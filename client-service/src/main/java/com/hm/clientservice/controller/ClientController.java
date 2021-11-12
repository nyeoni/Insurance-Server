package com.hm.clientservice.controller;

import com.hm.clientservice.controller.dto.AddClientDto;
import com.hm.clientservice.controller.dto.DetailClientDto;
import com.hm.clientservice.domain.Client;
import com.hm.clientservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("client-api")
@RequiredArgsConstructor
@RestController
public class ClientController {

    private final ClientService clientService;

    @GetMapping({"","{id}"})
    public ResponseEntity findClient(@PathVariable(required = false) Long id){
        if(id==null) {
            log.info("Client 전체 조회");
            return ResponseEntity.ok(clientService.findAll().stream().map(client -> new DetailClientDto(client)));
        }
        log.info("Client: {} 조회",id);
        return ResponseEntity.ok(new DetailClientDto(clientService.findById(id)));
    }


    @PostMapping
    public ResponseEntity<DetailClientDto> addClient(@RequestBody AddClientDto addClientDto){
        log.info("client 생성");
        return ResponseEntity.ok(new DetailClientDto(clientService.addClient(addClientDto)));
    }




}
