package com.hm.contractservice.client;

import com.hm.contractservice.client.dto.ClientDetailDto;
import com.hm.contractservice.global.EntityBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("client-service")
public interface ClientService {

    @RequestMapping(method = RequestMethod.GET, path = "/client/{id}")
    ResponseEntity<EntityBody<ClientDetailDto>> findById(@PathVariable Long id);
    

}
