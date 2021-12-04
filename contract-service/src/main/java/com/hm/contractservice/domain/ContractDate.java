package com.hm.contractservice.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@Embeddable
public class ContractDate {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
