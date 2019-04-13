package com.kodilla.ecommercee.cart.repository;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderRepository;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductRepository;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.repository.UserRepository;
import org.apache.catalina.LifecycleState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartRepositoryTest {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    private Logger LOGGER = LoggerFactory.getLogger(CartRepositoryTest.class);

    @Test
    public void save() {
        //Given
        Cart cart = new Cart();
        //When
        cartRepository.save(cart);
        Optional<Cart> foundCart = cartRepository.findById(cart.getCartId());
        //Then
        assertEquals(cart.getCartId(), foundCart.get().getCartId());
        //CleanUp
        cartRepository.deleteById(cart.getCartId());
    }

    @Test
    public void findById() {
        //Given
        Cart cart = new Cart();
        //When
        cartRepository.save(cart);
        Long foundCartId = cartRepository.findById(cart.getCartId()).get().getCartId();
        //Then
        assertNotNull(cartRepository.findById(cart.getCartId()));
        assertEquals(cart.getCartId(), foundCartId);
        //CleanUp
        cartRepository.deleteById(cart.getCartId());
        LOGGER.info("CleanUp Successful");
    }

    @Test
    public void shouldGiveUserId() {
        //Given
        User user = new User();
        userRepository.save(user);userRepository.save(user);userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);

        //When
        cartRepository.save(cart);
        Long userId = cartRepository.findById(cart.getCartId()).get().getUser().getUserId();
        System.out.println("************" + userId);

        //Then
        assertEquals(user.getUserId(), userId);

        //CleanUp
        cartRepository.deleteById(cart.getCartId());
        userRepository.deleteById(user.getUserId());
        LOGGER.info("CleanUp Successful");
    }

    @Test
    public void shouldGiveTwoProducts() {
        //Given
        Product product1 = new Product();
        Product product2 = new Product();
        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        Cart cart = new Cart();
        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        product1.setCartList(cartList);
        product2.setCartList(cartList);
        cart.setProductList(productList);

        //When
        cartRepository.save(cart);
        productRepository.save(product1);
        productRepository.save(product2);

        //Then
        int productListSize = cartRepository.findById(cart.getCartId()).get().getProductList().size();
        assertEquals(2, productListSize);

        //CleanUp
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());
        cartRepository.deleteById(cart.getCartId());
        LOGGER.info("CleanUp Successful");
    }
}