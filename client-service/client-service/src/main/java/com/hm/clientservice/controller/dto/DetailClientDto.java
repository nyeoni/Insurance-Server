package com.hm.clientservice.controller.dto;

import com.hm.clientservice.domain.Client;
import com.hm.clientservice.domain.embedded.AdditionalInfo;
import com.hm.clientservice.domain.embedded.Privacy;
import lombok.Getter;

@Getter
public class DetailClientDto {

    private Long id;

    private String name;

    private Privacy privacy;

    private AdditionalInfo additionalInfo;

    public DetailClientDto(Client client) {
        id = client.getId();
        name = client.getName();
        privacy = client.getPrivacy();
        additionalInfo = client.getAdditionalInfo();
    }
}
