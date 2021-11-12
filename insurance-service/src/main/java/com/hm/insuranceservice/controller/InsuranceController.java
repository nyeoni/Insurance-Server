package com.hm.insuranceservice.controller;

import com.hm.insuranceservice.controller.dto.AddInsuranceDto;
import com.hm.insuranceservice.controller.dto.InsuranceDetailDto;
import com.hm.insuranceservice.domain.Insurance;
import com.hm.insuranceservice.global.ResponseDto;
import com.hm.insuranceservice.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("insurance-api")
@RestController
public class InsuranceController {

    private final InsuranceService insuranceService;

    @GetMapping({"","{id}"})
    public ResponseEntity findInsurance(@PathVariable(required = false) Long id){
        if (id==null){
            return ResponseEntity.ok(ResponseDto.builder()
                    .message("보험 전체 조회").data(insuranceService.findAll()).build());
        }
        log.info("Insurance ID: {} 조회",id);
        return ResponseEntity.ok(ResponseDto.builder()
                .message("보험 ID:"+id).data(insuranceService.findById(id)).build());
    }

    @PostMapping
    public ResponseEntity addInsurance(@Validated @RequestBody AddInsuranceDto addInsuranceDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error(String.valueOf(bindingResult.getAllErrors()));
            return ResponseEntity.badRequest().build();
        }
        Insurance insurance = insuranceService.addInsurance(addInsuranceDto);
        return ResponseEntity.ok(InsuranceDetailDto.byInsurance(insurance));
    }
}
