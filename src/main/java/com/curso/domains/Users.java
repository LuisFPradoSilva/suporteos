package com.curso.domains;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Users extends Person {

    private List<ServiceOrder> serviceOrders = new ArrayList<>();

    public Users(UUID id, String firstName, String lastName, String cpf, String email, String password) {
        super(id, firstName, lastName, cpf, email, password);
    }

    public Users() {
        super();
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

}
