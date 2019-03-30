package com.kodilla.ecommercee.cart.controller;

import com.kodilla.ecommercee.cart.domain.CartDto;
import com.kodilla.ecommercee.cart.domain.UserDto;
import com.kodilla.ecommercee.product.controller.ProductController;
import com.kodilla.ecommercee.product.dto.ProductDto;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CartControllerTest {
    private CartController cartController = new CartController();
    private UserDto userDto = new UserDto();

    @Test
    public void createNewCart() {
        Long cartId = cartController.createNewCart(userDto);
        assertEquals(25L, cartId, 0.001);
    }

    @Test
    public void getProductsFromCart() {
        List<ProductDto> productDtoList = cartController.getProductsFromCart(25L);
        assertEquals(0, productDtoList.size());
    }

    @Test
    public void addProductToCart() {
        CartDto cartDto = cartController.addProductToCart(25L, 25L);
        assertEquals(25L, cartDto.getCartId(), 0.001);
    }
}