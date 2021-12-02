package com.hm.clientservice.domain.embedded;

import com.hm.clientservice.domain.constants.Bank;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Embeddable
public class AdditionalInfo {

    @Enumerated(value = EnumType.STRING)
    private Bank bank;

    private String buildingNumber;

    private String carNumber;

    private String driverLicenseNumber;

    private String passportNumber;

}
