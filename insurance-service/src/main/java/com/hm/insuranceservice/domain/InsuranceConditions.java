package com.hm.insuranceservice.domain;

import com.hm.insuranceservice.domain.constants.Rating;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@Embeddable
@Setter @Getter
public class InsuranceConditions {

    @Enumerated(value = EnumType.STRING)
    private Rating rating;

    private int startAge;
    private int endAge;

}
