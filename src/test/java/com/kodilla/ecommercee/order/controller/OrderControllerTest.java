package com.kodilla.ecommercee.order.controller;

import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.dto.OrderDto;
import com.kodilla.ecommercee.order.service.OrderDbService;
import com.kodilla.ecommercee.product.domain.Product;

import com.kodilla.ecommercee.product.service.ProductService;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {
    @Autowired
    OrderController orderController;
    @Autowired
    OrderDbService orderDbService;
    @Autowired
    ProductService productService;
    @Autowired
    UserRepository userRepository;

    @Test
    public void testShouldGetAllOrders() {
        //Given
        int ordersBeforeTest = orderDbService.getAllOrders().size();

        Product product1 = new Product("Product 1", "Product 1", 10.0, 1L, 1L);
        Product product2 = new Product("Product 2", "Product 2", 11.0, 2L, 2L);
        Product product3 = new Product("Product 3", "Product 3", 12.0, 3L, 3L);
        Product product4 = new Product("Product 4", "Product 4", 13.0, 4L, 4L);

        List<Product> productList1 = new ArrayList<>();
        productList1.add(product1);
        productList1.add(product2);
        List<Product> productList2 = new ArrayList<>();
        productList2.add(product3);
        productList2.add(product4);

        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);

        User user = new User();

        userRepository.save(user);

        Order order1 = new Order("Order 1", productList1, user);
        Order order2 = new Order("Order 2", productList2, user);

        orderDbService.saveOrder(order1);
        orderDbService.saveOrder(order2);

        int ordersAfterTest = orderDbService.getAllOrders().size();

        //When
        List<OrderDto> orderList = orderController.getOrders();

        //Then
        Assert.assertNotEquals(ordersBeforeTest, ordersAfterTest);
        Assert.assertEquals(ordersAfterTest, orderList.size());

        //CleanUp
        productService.deleteProduct(product1.getId());
        productService.deleteProduct(product2.getId());
        productService.deleteProduct(product3.getId());
        productService.deleteProduct(product4.getId());

        orderDbService.deleteOrder(order1.getOrderId());
        orderDbService.deleteOrder(order2.getOrderId());

        userRepository.deleteById(user.getUserId());
    }

    @Test
    public void testShouldGetOrderById() throws Exception {
        //Given
        Product product1 = new Product("Product 1", "Product 1", 10.0, 1L, 1L);
        Product product2 = new Product("Product 2", "Product 2", 11.0, 2L, 2L);
        Product product3 = new Product("Product 3", "Product 3", 12.0, 3L, 3L);
        Product product4 = new Product("Product 4", "Product 4", 13.0, 4L, 4L);

        List<Product> productList1 = new ArrayList<>();
        productList1.add(product1);
        productList1.add(product2);
        List<Product> productList2 = new ArrayList<>();
        productList2.add(product3);
        productList2.add(product4);

        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);

        User user = new User();

        userRepository.save(user);

        Order order1 = new Order("Order 1", productList1, user);
        Order order2 = new Order("Order 2", productList2, user);

        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        List<Order> orderList2 = new ArrayList<>();
        orderList2.add(order2);

        product1.setOrderList(orderList);
        product2.setOrderList(orderList);
        product3.setOrderList(orderList2);
        product4.setOrderList(orderList2);

        orderDbService.saveOrder(order1);
        orderDbService.saveOrder(order2);

        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);

        //When
        Order order1FromController = orderController.orderMapper.mapToOrder(orderController.getOrder(order1.getOrderId()));
        Order order2FromController = orderController.orderMapper.mapToOrder(orderController.getOrder(order2.getOrderId()));

        //Then
        Assert.assertEquals("Order 1", order1FromController.getOrderDescription());
        Assert.assertEquals("Product 1", order1FromController.getProductList().get(0).getName());
        Assert.assertEquals("Order 2", order2FromController.getOrderDescription());
        Assert.assertEquals("Product 3", order2FromController.getProductList().get(0).getName());

        //CleanUp
        productService.deleteProduct(product1.getId());
        productService.deleteProduct(product2.getId());
        productService.deleteProduct(product3.getId());
        productService.deleteProduct(product4.getId());

        orderDbService.deleteOrder(order1.getOrderId());
        orderDbService.deleteOrder(order2.getOrderId());

        userRepository.deleteById(user.getUserId());
    }

    @Test
    public void testShouldCreateOrder() {
        //Given
        Product product1 = new Product("Product 1", "Description 1", 10.0, 1L, 1L);
        Product product2 = new Product("Product 2", "Description 2", 11.0, 2L, 2L);
        Product product3 = new Product("Product 3", "Description 3", 12.0, 3L, 3L);
        Product product4 = new Product("Product 4", "Description 4", 13.0, 4L, 4L);

        List<Product> productList1 = new ArrayList<>();
        productList1.add(product1);
        productList1.add(product2);
        List<Product> productList2 = new ArrayList<>();
        productList2.add(product3);
        productList2.add(product4);

        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);

        User user = new User();

        userRepository.save(user);

        OrderDto orderDto1 = new OrderDto(1L, "Order Dto 1", productList1, user);
        OrderDto orderDto2 = new OrderDto(2L, "Order Dto 2", productList2, user);

        //When
        orderController.createOrder(orderDto1);
        orderController.createOrder(orderDto2);
        try {
            Long orderDto1Id = orderController.getOrder(orderDto1.getOrderId()).getOrderId();
            Long orderDto2Id = orderController.getOrder(orderDto2.getOrderId()).getOrderId();


            productService.saveProduct(product1);
            productService.saveProduct(product2);
            productService.saveProduct(product3);
            productService.saveProduct(product4);
            //Then
            Assert.assertEquals(2, orderController.getOrders().size());
            Assert.assertEquals("Product 3", orderController.getOrder(orderDto2Id).getProductList().get(0).getName());

            //CleanUp
            productService.deleteProduct(product1.getId());
            productService.deleteProduct(product2.getId());
            productService.deleteProduct(product3.getId());
            productService.deleteProduct(product4.getId());

            orderDbService.deleteOrder(orderDto1Id);
            orderDbService.deleteOrder(orderDto2Id);

            userRepository.deleteById(user.getUserId());

        } catch (OrderNotFoundException ignored) {
        }
    }

    @Test
    public void testShouldUpdateOrder() {
        //Given
        Product product1 = new Product("Product 1", "Product 1", 10.0, 1L, 1L);
        Product product2 = new Product("Product 2", "Product 2", 11.0, 2L, 2L);
        Product product3 = new Product("Product 3", "Product 3", 12.0, 3L, 3L);
        Product product4 = new Product("Product 4", "Product 4", 13.0, 4L, 4L);

        List<Product> productList1 = new ArrayList<>();
        productList1.add(product1);
        productList1.add(product2);
        List<Product> productList2 = new ArrayList<>();
        productList2.add(product3);
        productList2.add(product4);

        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);

        User user = new User();

        userRepository.save(user);

        OrderDto orderDto1 = new OrderDto(1L, "Order Dto 1", productList1, user);
        OrderDto orderDto2 = new OrderDto(2L, "Order Dto 2", productList2, user);

        orderController.createOrder(orderDto1);
        orderController.createOrder(orderDto2);

        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);

        //When
        orderDto1.setOrderDescription("Updated Order");
        orderDto2.setOrderId(3L);
        orderController.updateOrder(orderDto1);
        orderController.updateOrder(orderDto2);

        try {
            Long orderDto1Id = orderController.getOrder(orderDto1.getOrderId()).getOrderId();
            Long orderDto2Id = orderController.getOrder(orderDto2.getOrderId()).getOrderId();

            //Then
            Assert.assertEquals("Updated Order", orderController.getOrder(orderDto1Id).getOrderDescription());
            Assert.assertEquals(orderDto2.getOrderId(), orderController.getOrder(orderDto2Id).getOrderId());

            //CleanUp
            productService.deleteProduct(product1.getId());
            productService.deleteProduct(product2.getId());
            productService.deleteProduct(product3.getId());
            productService.deleteProduct(product4.getId());

            orderDbService.deleteOrder(orderDto1Id);
            orderDbService.deleteOrder(orderDto2Id);

            userRepository.deleteById(user.getUserId());
        } catch (OrderNotFoundException ignored) {
        }
    }

    @Test
    public void testShouldDeleteOrder() {
        //Given
        Product product1 = new Product("Product 1", "Product 1", 10.0, 1L, 1L);
        Product product2 = new Product("Product 2", "Product 2", 11.0, 2L, 2L);
        Product product3 = new Product("Product 3", "Product 3", 12.0, 3L, 3L);
        Product product4 = new Product("Product 4", "Product 4", 13.0, 4L, 4L);

        List<Product> productList1 = new ArrayList<>();
        productList1.add(product1);
        productList1.add(product2);
        List<Product> productList2 = new ArrayList<>();
        productList2.add(product3);
        productList2.add(product4);

        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);

        User user = new User();

        userRepository.save(user);

        OrderDto orderDto1 = new OrderDto(1L, "Order Dto 1", productList1, user);
        OrderDto orderDto2 = new OrderDto(2L, "Order Dto 2", productList2, user);

        orderController.createOrder(orderDto1);
        orderController.createOrder(orderDto2);

        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);

        //When
        try {
            Long orderDto1Id = orderController.getOrder(orderDto1.getOrderId()).getOrderId();
            Long orderDto2Id = orderController.getOrder(orderDto2.getOrderId()).getOrderId();

            orderController.deleteOrder(orderDto2Id);

            //Then
            Assert.assertEquals(1, orderController.getOrders().size());

            //CleanUp
            productService.deleteProduct(product1.getId());
            productService.deleteProduct(product2.getId());
            productService.deleteProduct(product3.getId());
            productService.deleteProduct(product4.getId());

            orderDbService.deleteOrder(orderDto1Id);

            userRepository.deleteById(user.getUserId());
        } catch (OrderNotFoundException ignored) {
        }
    }
}