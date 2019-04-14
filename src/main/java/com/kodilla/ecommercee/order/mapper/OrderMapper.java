package com.kodilla.ecommercee.order.mapper;

import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(
                orderDto.getOrderId(),
                orderDto.getOrderDescription(),
                orderDto.getProductList(),
                orderDto.getUser());
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getOrderDescription(),
                order.getProductList(),
                order.getUser());
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(orderDto -> new OrderDto(
                        orderDto.getOrderId(),
                        orderDto.getOrderDescription(),
                        orderDto.getProductList(),
                        orderDto.getUser()))
                .collect(Collectors.toList());
    }
}