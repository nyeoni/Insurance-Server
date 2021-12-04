package com.hm.insuranceservice.service;

import com.hm.insuranceservice.controller.dto.AddInsuranceDto;
import com.hm.insuranceservice.controller.dto.DetailInsuranceDto;
import com.hm.insuranceservice.domain.Insurance;

import java.util.List;

public interface InsuranceService {

    Insurance findById(Long id);

    List<Insurance> findAll();

    Insurance addInsurance(AddInsuranceDto addInsuranceDto);

    Insurance modifyInsurance(Long id,DetailInsuranceDto detailInsuranceDto);

    Boolean deleteInsuranceById(Long id);

}
