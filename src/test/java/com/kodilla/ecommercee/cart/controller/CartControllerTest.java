package com.kodilla.ecommercee.cart.controller;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.dto.CartDto;
import com.kodilla.ecommercee.cart.exception.CartNotFoundException;
import com.kodilla.ecommercee.cart.repository.CartRepository;
import com.kodilla.ecommercee.order.repository.OrderRepository;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.dto.ProductDto;
import com.kodilla.ecommercee.product.exception.ProductNotFoundException;
import com.kodilla.ecommercee.product.repository.ProductRepository;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.dto.UserDto;
import com.kodilla.ecommercee.user.exception.UserNotFoundException;
import com.kodilla.ecommercee.user.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.event.ComponentListener;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartControllerTest {
    @Autowired
    CartController cartController;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    Logger LOGGER = LoggerFactory.getLogger(CartControllerTest.class);


    @Test
    public void createNewCart() {
        //Given
        User user = new User();
        userRepository.save(user);
        //When
        try {
            Long cartId = cartController.createNewCart(user.getUserId());
            //Then
            assertEquals(user.getUserId(), cartRepository.findById(cartId).get().getUser().getUserId());
            LOGGER.info("Test OK");
            cartRepository.deleteById(cartId);
        } catch (UserNotFoundException u) {
            LOGGER.error("User not found");
        //Clean Up
        } finally {
            userRepository.deleteById(user.getUserId());
        }
    }

    @Test
    public void getProductsFromCart() {
        //Given
        Product testProduct1 = new Product("Test product 1", "Testing1", 111.0, 11L, 1L);
        Product testProduct2 = new Product("Test product 2", "Testing2", 222.0, 22L, 2L);
        Product testProduct3 = new Product("Test product 3", "Testing3", 333.0, 33L, 3L);
        productRepository.save(testProduct1);
        productRepository.save(testProduct2);
        productRepository.save(testProduct3);

        List<Product> productList = new ArrayList<>();
        productList.add(testProduct1);
        productList.add(testProduct2);
        productList.add(testProduct3);

        Cart cart = new Cart();

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        cart.setProductList(productList);
        testProduct1.setCartList(cartList);
        testProduct2.setCartList(cartList);
        testProduct3.setCartList(cartList);
        //When
        cartRepository.save(cart);
        productRepository.save(testProduct1);
        productRepository.save(testProduct2);
        productRepository.save(testProduct3);

        //Then
        try {
            List<ProductDto> productDtoList = cartController.getProductsFromCart(cart.getCartId());
            assertEquals(3, productDtoList.size());
            LOGGER.info("TEST OK");
        } catch (CartNotFoundException c) {
            LOGGER.error("Cart not found");
        //Clean Up
        } finally {
            productRepository.deleteById(testProduct1.getId());
            productRepository.deleteById(testProduct2.getId());
            productRepository.deleteById(testProduct3.getId());
            cartRepository.deleteById(cart.getCartId());
        }
    }

    @Test
    public void addProductToCart() {
        //Given
        User user = new User();
        userRepository.save(user);

        Product testProduct1 = new Product("Test product 1", "Testing1", 111.0, 11L, 1L);
        Product testProduct2 = new Product("Test product 2", "Testing2", 222.0, 22L, 2L);
        Product testProduct3 = new Product("Test product 3", "Testing3", 333.0, 33L, 3L);
        productRepository.save(testProduct1);
        productRepository.save(testProduct2);
        productRepository.save(testProduct3);
        //When
        List<Product> productList = new ArrayList<>();
        productList.add(testProduct1);
        productList.add(testProduct2);
        productList.add(testProduct3);

        Cart cart = new Cart();

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        cart.setUser(user);
        cart.setProductList(productList);
        testProduct1.setCartList(cartList);
        testProduct2.setCartList(cartList);

        //When
        cartRepository.save(cart);
        productRepository.save(testProduct1);
        productRepository.save(testProduct2);

        //Then
        try {
            CartDto cartDto = cartController.addProductToCart(testProduct3.getId(), cart.getCartId());
            assertEquals(3, cartDto.getProductDtoList().size());
            LOGGER.info("TEST OK");
        } catch (ProductNotFoundException | CartNotFoundException e) {
            LOGGER.error("Test failed");
        //CleanUp
        } finally {
            productRepository.deleteById(testProduct1.getId());
            productRepository.deleteById(testProduct2.getId());
            productRepository.deleteById(testProduct3.getId());
            cartRepository.deleteById(cart.getCartId());
            userRepository.deleteById(user.getUserId());
        }
    }

    @Test
    public void deleteProductFromCart() {
        //Given
        User user = new User();
        userRepository.save(user);

        Product testProduct1 = new Product("Test product 1", "Testing1", 111.0, 11L, 1L);
        Product testProduct2 = new Product("Test product 2", "Testing2", 222.0, 22L, 2L);
        Product testProduct3 = new Product("Test product 3", "Testing3", 333.0, 33L, 3L);
        productRepository.save(testProduct1);
        productRepository.save(testProduct2);
        productRepository.save(testProduct3);
        //When
        List<Product> productList = new ArrayList<>();
        productList.add(testProduct1);
        productList.add(testProduct2);
        productList.add(testProduct3);

        Cart cart = new Cart();

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        cart.setUser(user);
        cart.setProductList(productList);
        testProduct1.setCartList(cartList);
        testProduct2.setCartList(cartList);
        testProduct3.setCartList(cartList);

        //When
        cartRepository.save(cart);
        productRepository.save(testProduct1);
        productRepository.save(testProduct2);
        productRepository.save(testProduct3);

        //Then
        try {
            CartDto cartDto = cartController.deleteItemFromCart(testProduct3.getId(), cart.getCartId());
            assertEquals(2, cartDto.getProductDtoList().size());
            LOGGER.info("TEST OK");
        } catch (ProductNotFoundException | CartNotFoundException e) {
            LOGGER.error("TEST FAILED");
        } finally {
            productRepository.deleteById(testProduct1.getId());
            productRepository.deleteById(testProduct2.getId());
            productRepository.deleteById(testProduct3.getId());
            cartRepository.deleteById(cart.getCartId());
            userRepository.deleteById(user.getUserId());
        }
    }

}