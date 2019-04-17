package com.kodilla.ecommercee.order.repository;

import com.kodilla.ecommercee.order.domain.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderCrudOperationsTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void getOrder() {
        //given
        Order order = new Order(null, "aa", null, null);

        //when
        orderRepository.save(order);

        //then
        Assert.assertEquals("aa",order.getOrderDescription());

        //cleanup
        orderRepository.delete(order);

    }

    @Test
    public void createOrderTest() {
        //given

        Order order1 = new Order(null, "TEST", null, null);
        Order order2 = new Order(null, "TEST", null, null);
        Order order3 = new Order(null, "TEST", null, null);

        //when
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        //then
        Assert.assertEquals(3, orderRepository.findAll().size());

        //cleanup
        orderRepository.delete(order1);
        orderRepository.delete(order2);
        orderRepository.delete(order3);
    }

    @Test
    public void updateOrderTest() {
        //given
        Order order = new Order(null, "TEST", null, null);

        //when
        orderRepository.save(order);

        Long id1 = order.getOrderId();
        String newOrderDesc = "renamed order description";
        order.setOrderDescription(newOrderDesc);
        orderRepository.save(order);

        //then
        Assert.assertEquals(newOrderDesc, orderRepository.findById(id1).get().getOrderDescription());

        //cleanup
        orderRepository.delete(order);
    }

    @Test
    public void getAllOrdersTest() {
        //given

        Order order = new Order(null, "TEST", null, null);
        Order order1 = new Order(null, "TEST", null, null);
        Order order2 = new Order(null, "TEST", null, null);

        //when
        orderRepository.save(order);
        orderRepository.save(order1);
        orderRepository.save(order2);

        //then
        Assert.assertEquals(3, orderRepository.findAll().size());

        //cleanup
        orderRepository.delete(order);
        orderRepository.delete(order1);
        orderRepository.delete(order2);
    }


    @Test
    public void deleteOrder() {
        //given

        Order order = new Order(null, "test", null, null);

        //when
        orderRepository.save(order);
        orderRepository.delete(order);

        //then
        Assert.assertEquals(0, orderRepository.findAll().size());

    }

}
