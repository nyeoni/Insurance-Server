package com.hm.userservice.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Mapper {

    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());


    public static ObjectMapper getMapper() {
        return mapper;
    }
}
