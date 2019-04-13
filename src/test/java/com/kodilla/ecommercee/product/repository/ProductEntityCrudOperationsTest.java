package com.kodilla.ecommercee.product.repository;

import com.kodilla.ecommercee.cart.repository.CartRepository;
import com.kodilla.ecommercee.group.repository.GroupRepository;
import com.kodilla.ecommercee.order.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductEntityCrudOperationsTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void shouldCreateProduct() {

    }

    @Test
    public void shouldReadProduct() {

    }

    @Test
    public void shouldReadAllProducts() {

    }

    @Test
    public void shouldUpdateProduct() {

    }

    @Test
    public void shouldDeleteProduct() {

    }

}
