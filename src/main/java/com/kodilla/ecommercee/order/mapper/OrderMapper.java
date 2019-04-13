package com.kodilla.ecommercee.order.mapper;

import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order mapToOrder(final OrderDto orderDto) {
        return new Order();
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto();
    }
}