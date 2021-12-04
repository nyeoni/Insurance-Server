package com.hm.insuranceservice.domain;

import com.hm.insuranceservice.controller.dto.DetailInsuranceDto;
import com.hm.insuranceservice.domain.constants.Category;
import com.hm.insuranceservice.global.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Setter @Getter
@Entity
public class Insurance extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Category insuranceCategory;

    private String description;

    @Embedded
    private InsuranceConditions insuranceConditions;

    public Insurance modifyInsurance(DetailInsuranceDto insuranceDto){
        this.setName(insuranceDto.getName());
        this.setInsuranceCategory(insuranceDto.getCategory());
        this.setDescription(insuranceDto.getDescription());
        this.setInsuranceConditions(insuranceDto.getConditions());
        return this;
    }

}
