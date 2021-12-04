package com.hm.contractservice.controller.dto;

import com.hm.contractservice.domain.Channel;
import com.hm.contractservice.domain.Contract;
import com.hm.contractservice.domain.ContractDate;
import com.hm.contractservice.domain.ContractStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter @Setter
public class AddContractDto {

    @NotBlank
    private Long insuranceId;
    @NotBlank
    private Long clientId;
    private Long employeeId;

    private ContractDate contractDate;
    private Channel channel;
    private ContractStatus contractStatus;




    public Contract toContract(String insuranceName){
        return Contract.builder()
                .insuranceId(insuranceId)
                .insuranceName(insuranceName)
                .clientId(clientId)
                .employeeId(employeeId)
                .contractDate(contractDate)
                .channel(channel)
                .contractStatus(contractStatus)
                .build();
    }


}
