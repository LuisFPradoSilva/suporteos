package com.curso.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.domains.ServiceOrder;
import com.curso.domains.Technician;
import com.curso.domains.Users;
import com.curso.domains.dtos.ServiceOrderDTO;
import com.curso.domains.enums.OrderPriority;
import com.curso.domains.enums.OrderStatus;
import com.curso.repositories.ServiceOrderRepository;
import com.curso.services.exceptions.ObjectNotFoundException;

@Service
public class ServiceOrderService {

    @Autowired
    private ServiceOrderRepository serviceOrderRepo;

    @Autowired
    private TechnicianService techService;

    @Autowired
    private UsersService usersService;

    public List<ServiceOrderDTO> findAll() {
        return serviceOrderRepo.findAll().stream().map(obj -> new ServiceOrderDTO(obj)).collect(Collectors.toList());
    }

    public ServiceOrder findById(UUID id) {
        Optional<ServiceOrder> obj = serviceOrderRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Ordem de serviço não encontrada no sistema! ID: " + id));
    }

    private ServiceOrder newServiceOrder(ServiceOrderDTO obj) {
        Technician technician = techService.findById(obj.getTechnician());
        Users user = usersService.findById(obj.getUser());

        ServiceOrder os = new ServiceOrder();

        if(obj.getId() != null) {
            os.setId(obj.getId());
        }

        if(obj.getOrderStatus().equals(2)) {
            os.setEndData(LocalDate.now());
        }

        os.setTechnician(technician);
        os.setUsers(user);
        os.setOrderPriority(OrderPriority.toEnum(obj.getOrderPriority()));
        os.setOrderStatus(OrderStatus.toEnum(obj.getOrderStatus()));
        os.setTitleOS(obj.getTitleOS());
        os.setDescription(obj.getDescription());
        return os;
    }

    public ServiceOrder create(ServiceOrderDTO objDto) {
        return serviceOrderRepo.save(newServiceOrder(objDto));
    }

    public ServiceOrder update(UUID id, ServiceOrderDTO objDto) {
        objDto.setId(id);
        ServiceOrder oldObj = findById(id);
        oldObj = newServiceOrder(objDto);
        return serviceOrderRepo.save(oldObj);
    }

    public void delete(UUID id) {
        serviceOrderRepo.deleteById(id);
    }
}
