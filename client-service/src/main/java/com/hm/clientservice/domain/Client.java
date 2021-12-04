package com.hm.clientservice.domain;

import com.hm.clientservice.controller.dto.AddClientDto;
import com.hm.clientservice.domain.embedded.AdditionalInfo;
import com.hm.clientservice.domain.embedded.Privacy;
import com.hm.clientservice.global.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
public class Client extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    @Embedded
    private Privacy privacy;

    @Embedded
    private AdditionalInfo additionalInfo;


    public Client modifyClient(AddClientDto addClientDto) {
        this.setName(addClientDto.getName());
        this.setPrivacy(addClientDto.getPrivacy());
        this.setAdditionalInfo(addClientDto.getInfo());
        return this;
    }
}
