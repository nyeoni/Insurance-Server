package com.hm.clientservice.controller;

import com.hm.clientservice.controller.dto.AddClientDto;
import com.hm.clientservice.controller.dto.DetailClientDto;
import com.hm.clientservice.domain.Client;
import com.hm.clientservice.global.EntityBody;
import com.hm.clientservice.global.ErrorDto;
import com.hm.clientservice.global.MessageSourceHandler;
import com.hm.clientservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RequestMapping("/client")
@RequiredArgsConstructor
@RestController
public class ClientController {

    private final ClientService clientService;
    private final MessageSourceHandler ms;

    @GetMapping("/{id}")
    public ResponseEntity<EntityBody<DetailClientDto>> findClientById(@PathVariable Long id){
        Client findClient = clientService.findById(id);
        String message = ms.getMessage("Find.client", id);
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(DetailClientDto.byClient(findClient),message));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityBody<DetailClientDto>> modifyClient(@PathVariable Long id, @Validated @RequestBody AddClientDto addClientDto, BindingResult bindingResult){
        if(bindResultHasErrors(bindingResult)) {
            String message = ms.getMessage("Error.Modify.client");
            List<ErrorDto> errorDtoList = ErrorDto.byBindingResult(bindingResult, ms);
            log.error(message);
            return ResponseEntity.badRequest().body(EntityBody.badRequest(message,errorDtoList));
        }
        Client modifyClient = clientService.modifyClient(id,addClientDto);
        String message = ms.getMessage("Modify.client",modifyClient.getId());
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(DetailClientDto.byClient(modifyClient),message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EntityBody<DetailClientDto>> modifyClient(@PathVariable Long id){
        clientService.deleteClientById(id);
        String message = ms.getMessage("Delete.client",id);
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(message));
    }

    @GetMapping
    public ResponseEntity<EntityBody<Stream<DetailClientDto>>> findAll(){
        Stream<DetailClientDto> findAllClient = clientService.findAll().stream().map(client -> DetailClientDto.byClient(client));
        String message = ms.getMessage("Find.client","전체");
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(findAllClient,message));
    }

    @PostMapping
    public ResponseEntity addClient(@Validated @RequestBody AddClientDto addClientDto, BindingResult bindingResult){
        if(bindResultHasErrors(bindingResult)) {
            String message = ms.getMessage("Error.Add.client");
            List<ErrorDto> errorDtoList = ErrorDto.byBindingResult(bindingResult, ms);
            log.error(message);
            return ResponseEntity.badRequest().body(EntityBody.badRequest(message,errorDtoList));
        }
        Client addClient = clientService.addClient(addClientDto);
        String message = ms.getMessage("Add.client",addClientDto.getName());
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(DetailClientDto.byClient(addClient),message));
    }

    private boolean bindResultHasErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error(String.valueOf(bindingResult.getAllErrors()));
            return true;
        }
        return false;
    }


}
