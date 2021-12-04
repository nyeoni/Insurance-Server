package com.hm.insuranceservice.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Builder
@Getter @Setter
public class GetContractDto {
    private Long id;

    private Long insuranceId;
    private String insuranceName;

    private Long clientId;
    private Long employeeId;

    private ContractDate contractDate;
    private Channel channel;
    private ContractStatus contractStatus;

    public enum Channel {
        대면,전화,온라인
    }

    @Getter
    @Embeddable
    public class ContractDate {

        private LocalDateTime startDate;
        private LocalDateTime endDate;

    }

    public enum  ContractStatus {
        계약신청,계약중,계약거부,계약만기,부활,배서,제지급,계약해지
    }


}
