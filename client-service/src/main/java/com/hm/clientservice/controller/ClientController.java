package com.hm.clientservice.controller;

import com.hm.clientservice.controller.dto.AddClientDto;
import com.hm.clientservice.controller.dto.DetailClientDto;
import com.hm.clientservice.global.ErrorDto;
import com.hm.clientservice.global.MessageSourceHandler;
import com.hm.clientservice.global.ResponseDto;
import com.hm.clientservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ClientController {

    private final ClientService clientService;
    private final MessageSourceHandler ms;
    @GetMapping({"client","client/{id}"})
    public ResponseDto findClient(@PathVariable(required = false) Long id){
        if(id==null) {
            String message = ms.getMessage("find.client.all");
            log.info(message);
            return ResponseDto.builder().ok().message(message).data(clientService.findAll()).build();
        }
        String message = ms.getMessage("find.client.id", id);
        log.info(message);
        return ResponseDto.builder().ok().message(message).data(clientService.findById(id)).build();
    }


    @PostMapping
    public ResponseDto addClient(@Validated @RequestBody AddClientDto addClientDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            log.info(String.valueOf(bindingResult.getAllErrors()));
            return ResponseDto.builder().badRequest().errors(ErrorDto.byBindingResult(bindingResult,ms)).build();
        }
        String message = ms.getMessage("add.client",addClientDto.getName());
        log.info(message);
        return ResponseDto.builder().ok().message(message).data(DetailClientDto.byClient(clientService.addClient(addClientDto))).build();
    }




}
