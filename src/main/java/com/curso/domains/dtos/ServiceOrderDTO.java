package com.curso.domains.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.curso.domains.ServiceOrder;
import com.curso.domains.enums.OrderPriority;
import com.curso.domains.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ServiceOrderDTO {

    private UUID id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate = LocalDate.now();
    private LocalDate endDate;
    private String titleOS;
    private String description;
    private OrderPriority orderPriority;
    private OrderStatus orderStatus;

    public ServiceOrderDTO(ServiceOrder obj) {
        this.id = obj.getId();
        this.startDate = obj.getStartDate();
        this.endDate = obj.getEndData();
        this.titleOS = obj.getTitleOS();
        this.description = obj.getDescription();
        this.orderPriority = obj.getOrderPriority();
        this.orderStatus = obj.getOrderStatus();
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

    public OrderPriority getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(OrderPriority orderPriority) {
        this.orderPriority = orderPriority;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    
}
