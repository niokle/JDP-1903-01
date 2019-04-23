package com.kodilla.ecommercee.order.repository;

import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductRepository;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.repository.UserRepository;
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
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void testShouldFindAllOrders() {
        //Given
        Order testOrder1 = new Order();
        Order testOrder2 = new Order();
        orderRepository.save(testOrder1);
        orderRepository.save(testOrder2);

        //When
        List<Order> result = orderRepository.findAll();
        //Then
        Assert.assertEquals(2, result.size());
        //CleanUp
        orderRepository.delete(testOrder1);
        orderRepository.delete(testOrder2);
    }

    @Test
    public void testShouldSaveOrderWithProductList() {
        //Given
        Product testProduct1 = new Product();
        Product testProduct2 = new Product();
        productRepository.save(testProduct1);
        productRepository.save(testProduct2);

        List<Product> testProductList = new ArrayList<>();

        testProductList.add(testProduct1);
        testProductList.add(testProduct2);
        Order testOrder = new Order();
        testOrder.setOrderDescription("Description");
        testOrder.setProductList(testProductList);

        List<Order> testOrderList = new ArrayList<>();
        testOrderList.add(testOrder);

        testProduct1.setOrderList(testOrderList);
        testProduct2.setOrderList(testOrderList);
        //When
        orderRepository.save(testOrder);
        productRepository.save(testProduct1);
        productRepository.save(testProduct2);
        //Then
        Assert.assertEquals(1, orderRepository.count());
        Assert.assertEquals(2, orderRepository.findAll().get(0).getProductList().size());
        //CleanUp
        productRepository.deleteById(testProduct1.getId());
        productRepository.deleteById(testProduct2.getId());
        orderRepository.deleteById(testOrder.getOrderId());
    }
    @Test
    public void getOrder() {
        //given
        List<Product> aaa = new ArrayList<>();
        User user1 = new User();
        Order order = new Order(null, "aa", aaa, user1);

        //when
        userRepository.save(user1);
        orderRepository.save(order);

        //then
        Assert.assertEquals("aa", order.getOrderDescription());

        //cleanup
        orderRepository.delete(order);
        userRepository.delete(user1);

    }

    @Test
    public void createOrderTest() {
        //given
        List<Product> aaa = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        Order order1 = new Order(null, "TEST", aaa, user1);
        Order order2 = new Order(null, "TEST", aaa, user2);
        Order order3 = new Order(null, "TEST", aaa, user3);

        //when
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        //then
        Assert.assertEquals(3, orderRepository.findAll().size());

        //cleanup
        orderRepository.delete(order1);
        orderRepository.delete(order2);
        orderRepository.delete(order3);
        userRepository.delete(user1);
        userRepository.delete(user2);
        userRepository.delete(user3);
    }

    @Test
    public void updateOrderTest() {
        //given
        User user = new User();
        Order order = new Order(null, "TEST", null, user);

        //when
        userRepository.save(user);
        orderRepository.save(order);
        Long id1 = order.getOrderId();
        String newOrderDesc = "renamed order description";
        order.setOrderDescription(newOrderDesc);
        orderRepository.save(order);



        //then
        Assert.assertEquals(newOrderDesc, orderRepository.findById(id1).get().getOrderDescription());

        //cleanup
        orderRepository.delete(order);
        userRepository.delete(user);

    }

    @Test
    public void getAllOrdersTest() {
        //given
        List<Product> aaa = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        Order order = new Order(null, "TEST", aaa, user1);
        Order order1 = new Order(null, "TEST", aaa, user2);
        Order order2 = new Order(null, "TEST", aaa, user3);

        //when
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        orderRepository.save(order);
        orderRepository.save(order1);
        orderRepository.save(order2);

        //then
        Assert.assertEquals(3, orderRepository.findAll().size());

        //cleanup
        orderRepository.delete(order);
        orderRepository.delete(order1);
        orderRepository.delete(order2);
        userRepository.delete(user1);
        userRepository.delete(user2);
        userRepository.delete(user3);
    }


    @Test
    public void deleteOrder() {
        //given
        List<Product> aaa = new ArrayList<>();
        User user1 = new User();
        Order order = new Order(null, "test", aaa, user1);

        //when
        userRepository.save(user1);
        orderRepository.save(order);
        orderRepository.delete(order);
        userRepository.delete(user1);

        //then
        Assert.assertEquals(0, orderRepository.findAll().size());

    }
}