package com.kodilla.ecommercee.order.repository;

import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductRepository;
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
}