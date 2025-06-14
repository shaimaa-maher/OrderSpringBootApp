package com.example.myCoolApp.Services;

import com.example.myCoolApp.Daos.Specifications.OrderSpecifications;
import com.example.myCoolApp.Entities.Order;
import com.example.myCoolApp.Mapping.OrderMapper;
import com.example.myCoolApp.Model.Entities.Dtos.OrderDto;
import com.example.myCoolApp.Repositories.OrdersRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService{
    private final OrdersRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrdersRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }



    @Override
    @Transactional
    public OrderDto createOrder(OrderDto dto) {
        Order order = orderMapper.toEntity(dto);
        order.getOrderItemsList().forEach(item -> item.setOrderId(order));
        return orderMapper.toDTO(orderRepository.save(order));
    }

    @Override
    @Transactional
    public OrderDto updateOrder(Integer id, OrderDto dto) {
        Order currentOrder = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));

        // Update master  fields
        currentOrder.setCustomerName(dto.getCustomerName());
        currentOrder.setOrderDate(dto.getOrderDate());
        currentOrder.setTotalAmount(dto.getTotalAmount());


        currentOrder.getOrderItemsList().clear();

        if (dto.getOrderItemsList() != null) {
            dto.getOrderItemsList().forEach(itemDto -> {
                var orderItem = orderMapper.toEntity(itemDto);
                orderItem.setOrderId(currentOrder);
                currentOrder.getOrderItemsList().add(orderItem);
            });
        }

        return orderMapper.toDTO(orderRepository.save(currentOrder));
    }


    @Override
    public void deleteOrder(Integer id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found with id " + id);
        }
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDto getOrderById(Integer id) {
        Order order= orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));
        return orderMapper.toDTO(order);
    }

    @Override
    public Page<OrderDto> getFilteredOrders(Optional<String> customerName, Optional<Double> totalAmount,  Optional<Date> orderDate, Pageable pageable)
    {
        Specification<Order> spec = OrderSpecifications.filterOrders(
                customerName.orElse(null),
                orderDate.orElse(null),
                totalAmount.orElse(null)
        );
        return orderRepository.findAll(spec, pageable).map(orderMapper::toDTO);

    }
    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDTO)
                .toList();
    }

    @Override
    public Page<OrderDto> getAllOrdersPageable(Pageable pageable) {
        return orderRepository.findAll(pageable).map(orderMapper::toDTO);
    }

}