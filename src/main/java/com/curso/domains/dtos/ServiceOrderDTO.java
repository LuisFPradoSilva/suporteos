package com.curso.domains.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.curso.domains.ServiceOrder;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class ServiceOrderDTO {

    private UUID id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    @NotNull(message = "Campo obrigatório!")
    private String titleOS;

    @NotNull(message = "Campo obrigatório!")
    private String description;

    @NotNull(message = "Campo obrigatório!")
    private Integer orderPriority;

    @NotNull(message = "Campo obrigatório!")
    private Integer orderStatus;

    @NotNull(message = "Campo obrigatório!")
    private UUID technician;

    @NotNull(message = "Campo obrigatório!")
    private UUID user;
    private String nameTechnician;
    private String nameUser;

    public ServiceOrderDTO() {
        
    }

    public ServiceOrderDTO(ServiceOrder obj) {
        this.id = obj.getId();
        this.startDate = obj.getStartDate();
        this.endDate = obj.getEndData();
        this.titleOS = obj.getTitleOS();
        this.description = obj.getDescription();
        this.orderPriority = obj.getOrderPriority().getId();
        this.orderStatus = obj.getOrderStatus().getId();
        this.technician = obj.getTechnician().getId();
        this.user = obj.getUsers().getId();
        this.nameTechnician = obj.getTechnician().getFirstName() + " " + obj.getTechnician().getLastName();
        this.nameUser = obj.getUsers().getFirstName() + " " + obj.getUsers().getLastName();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTitleOS() {
        return titleOS;
    }

    public void setTitleOS(String titleOS) {
        this.titleOS = titleOS;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(Integer orderPriority) {
        this.orderPriority = orderPriority;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public UUID getTechnician() {
        return technician;
    }

    public void setTechnician(UUID technician) {
        this.technician = technician;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public String getNameTechnician() {
        return nameTechnician;
    }

    public void setNameTechnician(String nameTechnician) {
        this.nameTechnician = nameTechnician;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

}
