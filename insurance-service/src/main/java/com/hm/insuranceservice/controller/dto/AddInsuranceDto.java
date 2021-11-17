package com.hm.insuranceservice.controller.dto;

import com.hm.insuranceservice.domain.Insurance;
import com.hm.insuranceservice.domain.InsuranceConditions;
import com.hm.insuranceservice.domain.constants.Category;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter @Getter
public class AddInsuranceDto {

    @NotBlank
    private String name;

    private Category category;

    private String description;

    private InsuranceConditions conditions;


    public Insurance toInsurance(){
        return Insurance.builder()
                .name(name)
                .insuranceCategory(category)
                .description(description)
                .insuranceConditions(conditions)
                .build();
    }


}
