package com.curso.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.domains.dtos.ServiceOrderDTO;
import com.curso.repositories.ServiceOrderRepository;

@Service
public class ServiceOrderService {

    @Autowired
    private ServiceOrderRepository serviceOrderRepo;

    public List<ServiceOrderDTO> findAll() {
        return serviceOrderRepo.findAll().stream().map(obj -> new ServiceOrderDTO(obj)).collect(Collectors.toList());
    }
}
