package com.hm.contractservice.controller.dto;

import com.hm.contractservice.client.dto.ClientDetailDto;
import com.hm.contractservice.client.dto.InsuranceDetailDto;
import com.hm.contractservice.client.dto.UserDetailDto;
import com.hm.contractservice.domain.Channel;
import com.hm.contractservice.domain.Contract;
import com.hm.contractservice.domain.ContractDate;
import com.hm.contractservice.domain.ContractStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class ContractDto {
    private Long id;
    private Long insuranceId;
    private Long clientId;
    private Long employeeId;

    private ContractDate contractDate;
    private Channel channel;
    private ContractStatus contractStatus;

    public static ContractDto byContract(Contract contract){
        return ContractDto.builder()
                .id(contract.getId())
                .insuranceId(contract.getInsuranceId())
                .clientId(contract.getClientId())
                .employeeId(contract.getEmployeeId())
                .contractDate(contract.getContractDate())
                .channel(contract.getChannel())
                .contractStatus(contract.getContractStatus())
                .build();
    }
}
