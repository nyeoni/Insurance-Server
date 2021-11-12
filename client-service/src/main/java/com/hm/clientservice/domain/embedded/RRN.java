package com.hm.clientservice.domain.embedded;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class RRN {

    private String rrnFront;

    private String rrnBack;

}
