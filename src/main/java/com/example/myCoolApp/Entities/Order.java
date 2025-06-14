package com.example.myCoolApp.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@XmlRootElement
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o")
        , @org.hibernate.annotations.NamedQuery(name = "Order.findById", query = "SELECT o FROM Order o WHERE o.id = :id")
        , @org.hibernate.annotations.NamedQuery(name = "Order.findByName", query = "SELECT o FROM Order o WHERE o.customerName = :customerName")
        , @org.hibernate.annotations.NamedQuery(name = "Order.findByOrderDate", query = "SELECT o FROM Order o WHERE o.orderDate = :orderDate")
        })
public class Order implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq_gen")
  @SequenceGenerator(name = "order_seq_gen", sequenceName = "ORDERS_id", allocationSize = 1)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;


  @NotNull(message = "Order date is required")
  @Column(name = "ORDER_DATE")
  @Temporal(TemporalType.DATE)
  private Date orderDate;


  @Basic(optional = false)
  @NotNull(message = "Customer name is required")
  @Size(min = 1, max = 100)
  @Column(name = "CUSTOMER_NAME")
  private String customerName;


  @Basic(optional = false)
  @NotNull(message = "Total Amount is Required!")
  @Positive(message = "Amount must be positive")
  @Column(name = "TOTAL_AMOUNT")
  private Double totalAmount;

  @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> orderItemsList = new ArrayList<>();


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

  public List<OrderItem> getOrderItemsList() {
    return orderItemsList;
  }

  public void setOrderItemsList(List<OrderItem> orderItemsList) {
    this.orderItemsList = orderItemsList;
  }
}