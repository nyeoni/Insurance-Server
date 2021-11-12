package com.hm.clientservice.controller.dto;

import com.hm.clientservice.domain.Client;
import com.hm.clientservice.domain.embedded.AdditionalInfo;
import com.hm.clientservice.domain.embedded.Privacy;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class AddClientDto {

    private String name;

    private Privacy privacy;

    private AdditionalInfo info;


    public Client toClient(){
        return Client.builder()
                .name(name)
                .privacy(privacy)
                .additionalInfo(info)
                .build();
    }


}
