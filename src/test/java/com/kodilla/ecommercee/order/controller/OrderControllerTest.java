package com.kodilla.ecommercee.order.controller;

import com.kodilla.ecommercee.order.dto.OrderDto;
import com.kodilla.ecommercee.product.dto.ProductDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {

    @Autowired
    private OrderController orderController;

    @Test
    public void testShouldGetOrders() {
        //Given
        //When
        List<OrderDto> orderList = orderController.getOrders();
        //Then
        Assert.assertEquals(0, orderList.size());
    }

    @Test
    public void testShouldGetOrder() {
        //Given
        //When
        OrderDto orderDto = orderController.getOrder(1L);
        //Then
        Assert.assertNull(orderDto.getOrderId());
        Assert.assertNull(orderDto.getOrderDescription());
        Assert.assertNull(orderDto.getProductList());
    }

    @Test
    public void testShouldUpdateOrder() {
        //Given
        ProductDto product = new ProductDto();
        List<ProductDto> productList = new ArrayList<>();
        productList.add(product);
        OrderDto testOrder = new OrderDto(1L, "Test Order", productList);
        //When
        OrderDto orderDto = orderController.updateOrder(testOrder);
        //Then
        Assert.assertEquals("Test Order", orderDto.getOrderDescription());
        Assert.assertEquals(productList, orderDto.getProductList());

    }
}
