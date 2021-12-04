package com.hm.insuranceservice.client;

import com.hm.insuranceservice.global.EntityBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("contract-service")
public interface ContractClient {

    @RequestMapping(method = RequestMethod.PUT, path = "/contract/insurance/{id}")
    ResponseEntity<EntityBody<GetContractDto>> modifyContractInsuranceName(@PathVariable Long id, @RequestParam String insuranceName);
}
