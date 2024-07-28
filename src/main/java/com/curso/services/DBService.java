package com.curso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.domains.ServiceOrder;
import com.curso.domains.Technician;
import com.curso.domains.Users;
import com.curso.domains.enums.OrderPriority;
import com.curso.domains.enums.OrderStatus;
import com.curso.repositories.ServiceOrderRepository;
import com.curso.repositories.TechnicianRepository;
import com.curso.repositories.UsersRepository;

@Service
public class DBService {

    @Autowired
    private TechnicianRepository techRepo;

    @Autowired
    private UsersRepository usersRepo;

    @Autowired
    private ServiceOrderRepository serviceOrderRepo;

    public void initDB() {

        Technician technician1 = new Technician(null, "Luís Felipe", "Prado da Silva", "77788995", "lf@gmail.com", "01234567");
        Users users1 = new Users(null, "Fagundes", "Silveira", "552235415", "fagundao@outlook.com", "12345678");
        ServiceOrder serviceOrder1 = new ServiceOrder(null, "Serviço 1", "Descrição do Serviço", OrderPriority.MEDIUM, OrderStatus.OPEN, technician1, users1);

        techRepo.save(technician1);
        usersRepo.save(users1);
        serviceOrderRepo.save(serviceOrder1);
    }
}
