package com.hm.insuranceservice.controller;

import com.hm.insuranceservice.controller.dto.AddInsuranceDto;
import com.hm.insuranceservice.controller.dto.DetailInsuranceDto;
import com.hm.insuranceservice.domain.Insurance;
import com.hm.insuranceservice.global.EntityBody;
import com.hm.insuranceservice.global.ErrorDto;
import com.hm.insuranceservice.global.MessageSourceHandler;
import com.hm.insuranceservice.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RequestMapping("/insurance")
@RequiredArgsConstructor
@RestController
public class InsuranceController {

    private final InsuranceService insuranceService;
    private final MessageSourceHandler ms;

    @GetMapping("/{id}")
    public ResponseEntity<EntityBody<DetailInsuranceDto>> findInsurance(@PathVariable Long id){
        Insurance findInsurance = insuranceService.findById(id);
        String message = ms.getMessage("Find.insurance", id);
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(DetailInsuranceDto.byInsurance(findInsurance),message));
    }
    
    @GetMapping
    public ResponseEntity<EntityBody<Stream<DetailInsuranceDto>>> findAll(){
        Stream<DetailInsuranceDto> findAllInsurance = insuranceService.findAll().stream()
                .map(insurance -> DetailInsuranceDto.byInsurance(insurance));
        String message = ms.getMessage("Find.insurance","전체");
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(findAllInsurance,message));
    }

    @PostMapping
    public ResponseEntity addInsurance(@Validated @RequestBody AddInsuranceDto addInsuranceDto, BindingResult bindingResult){
        if(bindResultHasErrors(bindingResult)){
            String message = ms.getMessage("Error.Add.insurance");
            List<ErrorDto> errorDtoList = ErrorDto.byBindingResult(bindingResult,ms);
            log.error(message);
            return ResponseEntity.badRequest().body(EntityBody.badRequest(message,errorDtoList));
        }
        Insurance insurance = insuranceService.addInsurance(addInsuranceDto);
        String message = ms.getMessage("Add.insurance", insurance.getName());
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(insurance,message));
    }

    private boolean bindResultHasErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error(String.valueOf(bindingResult.getAllErrors()));
            return true;
        }
        return false;
    }

}
