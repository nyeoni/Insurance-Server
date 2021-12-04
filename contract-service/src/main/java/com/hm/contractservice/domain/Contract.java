package com.hm.contractservice.domain;

import com.hm.contractservice.controller.dto.ContractDto;
import com.hm.contractservice.global.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
public class Contract extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotBlank
    private Long insuranceId;
    private String insuranceName;
    @NotBlank
    private Long clientId;
    private Long employeeId;

    @Embedded
    private ContractDate contractDate;

    @Enumerated(value = EnumType.STRING)
    private Channel channel;

    @Enumerated(value = EnumType.STRING)
    private ContractStatus contractStatus;


    public Contract modifyContract(ContractDto contractDto) {
        this.setInsuranceId(contractDto.getInsuranceId());
        this.setClientId(contractDto.getClientId());
        this.setEmployeeId(contractDto.getEmployeeId());
        this.setContractDate(contractDto.getContractDate());
        this.setChannel(contractDto.getChannel());
        this.setContractStatus(contractDto.getContractStatus());
        return this;
    }
}
