package com.hm.contractservice.controller;

import com.hm.contractservice.client.ClientClient;
import com.hm.contractservice.client.InsuranceClient;
import com.hm.contractservice.client.UserClient;
import com.hm.contractservice.client.dto.ClientDetailDto;
import com.hm.contractservice.client.dto.InsuranceDetailDto;
import com.hm.contractservice.client.dto.UserDetailDto;
import com.hm.contractservice.controller.dto.AddContractDto;
import com.hm.contractservice.controller.dto.ContractDto;
import com.hm.contractservice.controller.dto.DetailContractDto;
import com.hm.contractservice.controller.dto.GetContractDto;
import com.hm.contractservice.domain.Contract;
import com.hm.contractservice.global.ErrorDto;
import com.hm.contractservice.global.MessageSourceHandler;
import com.hm.contractservice.global.EntityBody;
import com.hm.contractservice.service.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RequestMapping("/contract")
@RequiredArgsConstructor
@RestController
public class ContractController {

    private final ContractService contractService;
    private final MessageSourceHandler ms;
    private final ClientClient clientService;
    private final InsuranceClient insuranceService;
    private final UserClient userService;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @PostMapping
    public ResponseEntity addContract(@RequestBody AddContractDto addContractDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String message = ms.getMessage("Error.Add.addContractDto");
            List<ErrorDto> errorDtoList = ErrorDto.byBindingResult(bindingResult, ms);
            log.error(message);
            return ResponseEntity.badRequest().body(EntityBody.badRequest(message,errorDtoList));
        }
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
        ArrayList<ErrorDto> list = new ArrayList<>();
        ClientDetailDto client = findClientDetailDtoById(circuitBreaker, list, addContractDto.getClientId(), addContractDto.getClass().getName());
        InsuranceDetailDto insurance = findInsuranceDetailDtoById(circuitBreaker, list, addContractDto.getInsuranceId(), addContractDto.getClass().getName());
        if(list.size()>0)
            return ResponseEntity.internalServerError().body(EntityBody.serverError("contract 추가에 실패했습니다.",list));
        UserDetailDto employee = findUserDetailDtoById(addContractDto.getEmployeeId(), circuitBreaker);
        Contract contract = contractService.addContract(addContractDto,client,insurance,employee);

        String message = ms.getMessage("Add.contract", contract.getId());
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(DetailContractDto.byContract(contract,insurance,client,employee),message));
    }

    @PutMapping("/{id}")
    public ResponseEntity forceModifyContract(@PathVariable Long id, @RequestBody ContractDto modifyContractDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String message = ms.getMessage("Error.Modify.contractDto");
            List<ErrorDto> errorDtoList = ErrorDto.byBindingResult(bindingResult, ms);
            log.error(message);
            return ResponseEntity.badRequest().body(EntityBody.badRequest(message,errorDtoList));
        }
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
        ArrayList<ErrorDto> list = new ArrayList<>();
        ClientDetailDto client = findClientDetailDtoById(circuitBreaker, list, modifyContractDto.getClientId(), modifyContractDto.getClass().getName());
        InsuranceDetailDto insurance = findInsuranceDetailDtoById(circuitBreaker, list, modifyContractDto.getInsuranceId(), modifyContractDto.getClass().getName());
        if(list.size()>0)
            return ResponseEntity.internalServerError().body(EntityBody.serverError("contract 수정에 실패했습니다.",list));
        UserDetailDto employee = findUserDetailDtoById(modifyContractDto.getId(),circuitBreaker);
        Contract contract = contractService.forceModifyContract(id,modifyContractDto,insurance);

        String message = ms.getMessage("Modify.contract", contract.getId());
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(DetailContractDto.byContract(contract,insurance,client,employee),message));
    }

    @PutMapping("/insurance/{id}")
    public ResponseEntity forceModifyInsurance(@PathVariable Long id, @RequestParam String insuranceName){
        contractService.modifyContractInsuranceName(id, insuranceName);
        String message = ms.getMessage("Modify.contract.insuranceName", id);
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EntityBody<ContractDto>> forceDeleteContract(@PathVariable Long id){
        contractService.forceDeleteContract(id);
        String message = ms.getMessage("Delete.contract",id);
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(message));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityBody<GetContractDto>> findAllContract(@PathVariable Long id){
        Contract findContract = contractService.findById(id);
        String message = ms.getMessage("Find.contract",id);
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(GetContractDto.byContract(findContract),message));
    }

    @GetMapping
    public ResponseEntity<EntityBody<Stream<GetContractDto>>> findAllContract(){
        Stream<GetContractDto> contractDtoStream = contractService.findAll()
                .stream().map(contract -> GetContractDto.byContract(contract));
        String message = ms.getMessage("Find.contract","전체");
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(contractDtoStream,message));
    }


    private UserDetailDto findUserDetailDtoById(Long id, CircuitBreaker circuitBreaker) {
        UserDetailDto employee = null;
        if(id!=null)
            employee = circuitBreaker.run(() -> getData(userService.findById(id)));
        return employee;
    }

    private InsuranceDetailDto findInsuranceDetailDtoById(CircuitBreaker circuitBreaker, ArrayList<ErrorDto> list, Long insuranceId, String name) {
        return circuitBreaker.run(() -> getData(insuranceService.findById(insuranceId)),
                throwable -> {
                    String message = ms.getMessage("Error.Find.id", "보험", insuranceId);
                    list.add(new ErrorDto(name, "insuranceId", message));
                    log.error(message);
                    return null;
                });
    }

    private ClientDetailDto findClientDetailDtoById(CircuitBreaker circuitBreaker, ArrayList<ErrorDto> list, Long clientId, String name) {
        return circuitBreaker.run(() -> getData(clientService.findById(clientId)),
                throwable -> {
                    String message = ms.getMessage("Error.Find.id", "고객", clientId);
                    list.add(new ErrorDto(name, "clientId", message));
                    log.error(message);
                    return null;
                });
    }

    public <T> T getData(ResponseEntity<EntityBody<T>> responseEntity){
        return responseEntity.getBody().getData();
    }


}
