package com.hm.clientservice.domain.embedded;

import com.hm.clientservice.domain.constants.Bank;
import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class AdditionalInfo {

    private Bank bank;

    private String buildingNumber;

    private String carNumber;

    private String driverLicenseNumber;

    private String passportNumber;

}
