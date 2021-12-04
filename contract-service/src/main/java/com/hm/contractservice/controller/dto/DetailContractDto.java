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

@Builder
@Getter
public class DetailContractDto {

    private Long id;
    private InsuranceDetailDto insuranceId;
    private ClientDetailDto clientId;
    private UserDetailDto employeeId;

    private ContractDate contractDate;
    private Channel channel;
    private ContractStatus contractStatus;

    public static DetailContractDto byContract(Contract contract, InsuranceDetailDto insuranceDetailDto, ClientDetailDto clientDetailDto, UserDetailDto userDetailDto){
        return DetailContractDto.builder()
                .id(contract.getId())
                .insuranceId(insuranceDetailDto)
                .clientId(clientDetailDto)
                .employeeId(userDetailDto)
                .contractDate(contract.getContractDate())
                .channel(contract.getChannel())
                .contractStatus(contract.getContractStatus())
                .build();
    }

}
