package com.example.myCoolApp.Model.Entities.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDto {

    private Integer id;

    @NotNull(message = "Order date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @NotNull(message = "Customer Name is required")
    @NotEmpty
    @Size(min = 8)
    private String customerName;

    @Positive(message = "Amount must be positive")
    private Double totalAmount;

    private List<OrderItemDto> orderItemsList = new ArrayList<>();


    public OrderDto(Integer id, Date orderDate, String customerName, Double totalAmount, List<OrderItemDto> orderItemsList ) {
        this.id = id;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.orderItemsList  = orderItemsList ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItemDto> getOrderItemsList () {
        return orderItemsList ;
    }

    public void setOrderItemsList (List<OrderItemDto> orderItemsList ) {
        this.orderItemsList  = orderItemsList ;
    }
}
