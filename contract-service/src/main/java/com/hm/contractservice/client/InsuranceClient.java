package com.hm.contractservice.client;

import com.hm.contractservice.client.dto.InsuranceDetailDto;
import com.hm.contractservice.global.EntityBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("insurance-service")
public interface InsuranceClient {

    @RequestMapping(method = RequestMethod.GET, path = "/insurance/{id}")
    ResponseEntity<EntityBody<InsuranceDetailDto>> findById(@PathVariable Long id);

}
