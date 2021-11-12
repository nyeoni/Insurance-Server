package com.hm.clientservice.service;

import com.hm.clientservice.controller.dto.AddClientDto;
import com.hm.clientservice.domain.Client;
import com.hm.clientservice.domain.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepo clientRepo;

    @Override
    public Client findById(Long id) {
        return clientRepo.findById(id).orElseThrow();
    }

    @Override
    public List<Client> findAll() {
        return clientRepo.findAll();
    }

    @Override
    public Client addClient(AddClientDto addClientDto) {
        return clientRepo.save(addClientDto.toClient());
    }
}
