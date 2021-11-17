package com.hm.clientservice.controller.dto;

import com.hm.clientservice.domain.Client;
import com.hm.clientservice.domain.embedded.AdditionalInfo;
import com.hm.clientservice.domain.embedded.Privacy;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DetailClientDto {

    private Long id;

    private String name;

    private Privacy privacy;

    private AdditionalInfo additionalInfo;

    public static DetailClientDto byClient(Client client) {
        return new DetailClientDto(client.getId(),client.getName(),client.getPrivacy(),client.getAdditionalInfo());
    }
}
