package com.curso.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.domains.ServiceOrder;
import com.curso.domains.dtos.ServiceOrderDTO;
import com.curso.repositories.ServiceOrderRepository;
import com.curso.services.exceptions.ObjectNotFoundException;

@Service
public class ServiceOrderService {

    @Autowired
    private ServiceOrderRepository serviceOrderRepo;

    public List<ServiceOrderDTO> findAll() {
        return serviceOrderRepo.findAll().stream().map(obj -> new ServiceOrderDTO(obj)).collect(Collectors.toList());
    }

    public ServiceOrder findById(UUID id) {
        Optional<ServiceOrder> obj = serviceOrderRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Ordem de serviço não encontrada no sistema! ID: " + id));
    }

    public ServiceOrder create(ServiceOrderDTO objDto) {
        objDto.setId(null);
        ServiceOrder newObj = new ServiceOrder(objDto);
        return serviceOrderRepo.save(newObj);
    }

    public ServiceOrder update(UUID id, ServiceOrderDTO objDto) {
        objDto.setId(id);
        ServiceOrder oldObj = findById(id);
        oldObj = new ServiceOrder(objDto);
        return serviceOrderRepo.save(oldObj);
    }

    public void delete(UUID id) {
        ServiceOrder obj = findById(id);
        serviceOrderRepo.delete(obj);
    }
}
