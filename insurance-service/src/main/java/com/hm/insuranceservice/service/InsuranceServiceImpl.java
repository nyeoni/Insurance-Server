package com.hm.insuranceservice.service;

import com.hm.insuranceservice.controller.dto.AddInsuranceDto;
import com.hm.insuranceservice.domain.Insurance;
import com.hm.insuranceservice.domain.InsuranceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class InsuranceServiceImpl implements InsuranceService{

    private final InsuranceRepo insuranceRepo;

    @Override
    public Insurance findById(Long id) {
        return insuranceRepo.findById(id).orElseThrow();
    }

    @Override
    public List<Insurance> findAll() {
        return insuranceRepo.findAll();
    }

    @Override
    public Insurance addInsurance(AddInsuranceDto addInsuranceDto) {
        return insuranceRepo.save(addInsuranceDto.toInsurance());
    }

    @Override
    public void deleteInsuranceById(Long id) {
        Insurance insurance = insuranceRepo.findById(id).orElseThrow();
        insuranceRepo.delete(insurance);
    }
}
