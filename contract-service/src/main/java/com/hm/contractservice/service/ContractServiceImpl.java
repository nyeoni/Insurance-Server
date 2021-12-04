package com.hm.contractservice.service;


import com.hm.contractservice.client.dto.ClientDetailDto;
import com.hm.contractservice.client.dto.InsuranceDetailDto;
import com.hm.contractservice.client.dto.UserDetailDto;
import com.hm.contractservice.controller.dto.AddContractDto;
import com.hm.contractservice.controller.dto.ContractDto;
import com.hm.contractservice.domain.Contract;
import com.hm.contractservice.domain.ContractRepo;
import com.hm.contractservice.global.exception.InvalidFindException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ContractServiceImpl implements ContractService{

    private final ContractRepo contractRepo;

    @Override
    public Contract addContract(AddContractDto addContractDto, ClientDetailDto client, InsuranceDetailDto insurance, UserDetailDto user) {
        checkInsuranceConditions(client,insurance);
        return contractRepo.save(addContractDto.toContract(insurance.getName()));
    }

    @Override
    public Contract findById(Long id) {
        return contractRepo.findById(id).orElseThrow(() -> new InvalidFindException.ById());
    }

    @Override
    public List<Contract> findAll() {
        return contractRepo.findAll();
    }

    @Override
    public Contract forceModifyContract(Long id, ContractDto contractDto, InsuranceDetailDto insuranceDetailDto) {
        Contract contract = findById(id);
        contract.setInsuranceName(insuranceDetailDto.getName());
        Contract modifyContract = contract.modifyContract(contractDto);
        contractRepo.flush();
        return modifyContract;
    }

    @Override
    public Boolean forceDeleteContract(Long id) {
        contractRepo.deleteById(id);
        return true;
    }

    @Override
    public void modifyContractInsuranceName(Long id, String insuranceName) {
        contractRepo.bulkModifyInsuranceName(id, insuranceName);
    }

    private Boolean checkInsuranceConditions(ClientDetailDto client, InsuranceDetailDto insurance) {
        int age = client.getPrivacy().getAge();
        if(client.getPrivacy()==null||client.getPrivacy().getAge()==0)
            throw new RuntimeException();

        InsuranceDetailDto.InsuranceConditions conditions = insurance.getConditions();
        if(conditions.getStartAge()<=age&&age<=conditions.getEndAge()){
            return true;
        }
        return false;
    }

}
