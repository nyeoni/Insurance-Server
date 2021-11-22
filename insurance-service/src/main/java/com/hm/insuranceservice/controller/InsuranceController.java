package com.hm.insuranceservice.controller;

import com.hm.insuranceservice.controller.dto.AddInsuranceDto;
import com.hm.insuranceservice.controller.dto.InsuranceDetailDto;
import com.hm.insuranceservice.domain.Insurance;
import com.hm.insuranceservice.global.ErrorDto;
import com.hm.insuranceservice.global.MessageSourceHandler;
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
@RestController
public class InsuranceController {

    private final InsuranceService insuranceService;
    private final MessageSourceHandler ms;

    @GetMapping({"insurance","insurance/{id}"})
    public ResponseDto findInsurance(@PathVariable(required = false) Long id){
        if (id==null){
            String findAllMessage = ms.getMessage("find.Insurance.all");
            log.info(findAllMessage);
            return ResponseDto.builder().ok()
                    .message(findAllMessage).data(insuranceService.findAll()).build();
        }
        String findByIdMessage = ms.getMessage("find.Insurance.id", id);
        log.info(findByIdMessage);
        return ResponseDto.builder().ok()
                .message(findByIdMessage).data(insuranceService.findById(id)).build();
    }

    @PostMapping("insurance")
    public ResponseDto<InsuranceDetailDto> addInsurance(@Validated @RequestBody AddInsuranceDto addInsuranceDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error(String.valueOf(bindingResult.getAllErrors()));
            return ResponseDto.builder().badRequest().errors(ErrorDto.byBindingResult(bindingResult,ms)).build();
        }
        Insurance insurance = insuranceService.addInsurance(addInsuranceDto);
        String addMessage = ms.getMessage("add.Insurance", insurance.getName());
        log.info(addMessage);
        return ResponseDto.builder().ok().message(addMessage).data(InsuranceDetailDto.byInsurance(insurance)).build();
    }
}
