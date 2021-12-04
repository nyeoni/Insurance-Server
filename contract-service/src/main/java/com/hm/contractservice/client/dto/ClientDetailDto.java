package com.hm.contractservice.client.dto;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ClientDetailDto {

    private Long id;

    private String name;

    private Privacy privacy;

    private AdditionalInfo additionalInfo;

    @Getter
    public static class Privacy {

        private Gender gender;

        private RRN rrn;

        private int age;

        private String address;

        private String email;

        private String phoneNumber;

        @Getter
        public class RRN {

            private String rrnFront;

            private String rrnBack;

        }

        public enum Gender {
            MALE,FEMALE
        }

    }

    @Getter
    public static class AdditionalInfo {

        private Bank bank;

        private String buildingNumber;

        private String carNumber;

        private String driverLicenseNumber;

        private String passportNumber;

        public enum  Bank {
            국민,하나,우리
        }


    }

}
