package com.hm.contractservice.client.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class InsuranceDetailDto {

    private Long id;

    private String name;

    private Category category;

    private String description;

    private InsuranceConditions conditions;

    public enum Category {
        자동차,운전자,여행,화재
    }

    @Getter
    public static class InsuranceConditions {

        private Rating rating;

        private int startAge;
        private int endAge;

        public enum Rating {
            A,B,C,D,F
        }
    }



}
