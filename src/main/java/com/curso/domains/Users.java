package com.curso.domains;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.curso.domains.enums.PersonType;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Users extends Person {

    @OneToMany(mappedBy = "users")
    private List<ServiceOrder> serviceOrders = new ArrayList<>();

    public Users(UUID id, String firstName, String lastName, String cpf, String email, String password) {
        super(id, firstName, lastName, cpf, email, password);
        addPersonType(PersonType.USERS);
    }

    public Users() {
        super();
        addPersonType(PersonType.USERS);
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

}
