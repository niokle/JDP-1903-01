package com.kodilla.ecommercee.order.controller;

import com.kodilla.ecommercee.order.dto.OrderDto;
import com.kodilla.ecommercee.order.mapper.OrderMapper;
import com.kodilla.ecommercee.order.service.OrderDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("ecommercee/order")
public class OrderController {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderDbService orderDbService;

    @GetMapping(value = "getOrders")
    public List<OrderDto> getOrders() {
        return orderMapper.mapToOrderDtoList(orderDbService.getAllOrders());
    }

    @GetMapping(value = "getOrder")
    public OrderDto getOrder(@RequestParam Long id) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderDbService.findOrderByID(id).orElseThrow(OrderNotFoundException::new));
    }

    @PostMapping(value = "createOrder", consumes = APPLICATION_JSON_VALUE)
    public Long createOrder(@RequestBody OrderDto orderDto) {
        return orderDbService.saveOrder(orderMapper.mapToOrder(orderDto)).getOrderId();
    }

    @PutMapping(value = "updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderMapper.mapToOrderDto(orderDbService.saveOrder(orderMapper.mapToOrder(orderDto)));
    }

    @DeleteMapping(value = "deleteOrder")
    public void deleteOrder(@RequestParam Long id) {
        orderDbService.deleteOrder(id);
    }
}
