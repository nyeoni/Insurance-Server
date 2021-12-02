package com.hm.insuranceservice.controller.dto;

import com.hm.insuranceservice.domain.Insurance;
import com.hm.insuranceservice.domain.InsuranceConditions;
import com.hm.insuranceservice.domain.constants.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class DetailInsuranceDto {

    private Long id;

    private String name;

    private Category category;

    private String description;

    private InsuranceConditions conditions;

    public static DetailInsuranceDto byInsurance(Insurance insurance){
        return DetailInsuranceDto.builder()
                .id(insurance.getId())
                .name(insurance.getName())
                .category(insurance.getInsuranceCategory())
                .description(insurance.getDescription())
                .conditions(insurance.getInsuranceConditions())
                .build();
    }
}
