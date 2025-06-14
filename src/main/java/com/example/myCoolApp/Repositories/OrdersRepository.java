package com.example.myCoolApp.Repositories;

import com.example.myCoolApp.Entities.Order;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

@Repository
public interface  OrdersRepository extends  JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
List<Order> findAllByOrderDate(Date orderDate);

}
