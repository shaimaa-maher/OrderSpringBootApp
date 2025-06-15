package com.example.myCoolApp.Controllers;



import com.example.myCoolApp.Mapping.OrderMapper;
import com.example.myCoolApp.Model.Entities.Dtos.OrderDto;
import com.example.myCoolApp.Services.OrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private  OrderService orderService;

    @Autowired
    private  OrderMapper mapper;


    @PostMapping("/create")
    public ResponseEntity<OrderDto> create(@Valid @RequestBody OrderDto dto) {
        OrderDto saved = orderService.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Get all orders with items
    @GetMapping("/all")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();  // use service
    }

    // Get order by ID
    @GetMapping("/getOrder/{id}")
    public OrderDto getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Integer id, @Valid @RequestBody OrderDto orderDto) {
        OrderDto updatedOrder = orderService.updateOrder(id, orderDto);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order with ID " + id + " was successfully deleted.");
    }

    @GetMapping("/page")
    public Page<OrderDto> getOrdersPageable(Pageable pageable) {
        return orderService.getAllOrdersPageable(pageable);
    }

    @GetMapping("/filter")
    public Page<OrderDto> getFilteredOrders(
            @RequestParam Optional<String> customerName,
            @RequestParam Optional<Double> totalAmount,
            @RequestParam(name = "orderDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDate,
            Pageable pageable) {
        Optional<Date> optionalOrderDate = Optional.ofNullable(orderDate);
        return orderService.getFilteredOrders(customerName, totalAmount, optionalOrderDate, pageable);
    }
}
