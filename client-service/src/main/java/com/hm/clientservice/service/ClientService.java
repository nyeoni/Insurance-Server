package com.hm.clientservice.service;

import com.hm.clientservice.controller.dto.AddClientDto;
import com.hm.clientservice.domain.Client;

import java.util.List;

public interface ClientService {

    Client findById(Long id);

    List<Client> findAll();

    Client addClient(AddClientDto addClientDto);

}
