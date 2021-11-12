package com.hm.clientservice.domain;

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


}
