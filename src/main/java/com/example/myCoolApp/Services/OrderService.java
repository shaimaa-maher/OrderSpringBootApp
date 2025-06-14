package com.example.myCoolApp.Services;


import com.example.myCoolApp.Model.Entities.Dtos.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface  OrderService {

    OrderDto createOrder(OrderDto dto);
    OrderDto updateOrder(Integer id, OrderDto dto);
    void deleteOrder(Integer id);
    OrderDto getOrderById(Integer id);
    Page<OrderDto> getFilteredOrders(Optional<String> customerName,  Optional<Double> totalAmount, Optional<Date> orderDate, Pageable pageable);
    List<OrderDto> getAllOrders();
    Page<OrderDto> getAllOrdersPageable(Pageable pageable);

}
