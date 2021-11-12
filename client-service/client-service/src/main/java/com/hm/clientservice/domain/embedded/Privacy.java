package com.hm.clientservice.domain.embedded;

import com.hm.clientservice.domain.constants.Gender;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Embeddable
public class Privacy {

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Embedded
    private RRN rrn;

    private int age;

    private String address;

    private String email;

    private String phoneNumber;


}
