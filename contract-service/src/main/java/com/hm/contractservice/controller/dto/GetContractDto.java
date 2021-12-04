package com.hm.contractservice.controller.dto;

import com.hm.contractservice.domain.Channel;
import com.hm.contractservice.domain.Contract;
import com.hm.contractservice.domain.ContractDate;
import com.hm.contractservice.domain.ContractStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class GetContractDto {
    private Long id;

    private Long insuranceId;
    private String insuranceName;

    private Long clientId;
    private Long employeeId;

    private ContractDate contractDate;
    private Channel channel;
    private ContractStatus contractStatus;

    public static GetContractDto byContract(Contract contract){
        return GetContractDto.builder()
                .id(contract.getId())
                .insuranceId(contract.getInsuranceId())
                .insuranceName(contract.getInsuranceName())
                .clientId(contract.getClientId())
                .employeeId(contract.getEmployeeId())
                .contractDate(contract.getContractDate())
                .channel(contract.getChannel())
                .contractStatus(contract.getContractStatus())
                .build();
    }
}
