package com.hm.contractservice.service;

import com.hm.contractservice.client.dto.ClientDetailDto;
import com.hm.contractservice.client.dto.InsuranceDetailDto;
import com.hm.contractservice.client.dto.UserDetailDto;
import com.hm.contractservice.controller.dto.AddContractDto;
import com.hm.contractservice.controller.dto.ContractDto;
import com.hm.contractservice.domain.Contract;

import java.util.List;

public interface ContractService {

    Contract addContract(AddContractDto addContractDto, ClientDetailDto client, InsuranceDetailDto insurance, UserDetailDto user);

    Contract findById(Long id);

    List<Contract> findAll();

    Contract forceModifyContract(Long id, ContractDto contractDto, InsuranceDetailDto insuranceDetailDto);

    Boolean forceDeleteContract(Long id);

    void modifyContractInsuranceName(Long id, String insuranceName);
}
