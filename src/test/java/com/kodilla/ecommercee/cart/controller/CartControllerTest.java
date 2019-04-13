package com.kodilla.ecommercee.cart.controller;

import com.kodilla.ecommercee.cart.dto.CartDto;
import com.kodilla.ecommercee.product.dto.ProductDto;
import com.kodilla.ecommercee.user.dto.UserDto;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CartControllerTest {
    private CartController cartController = new CartController();
    private UserDto userDto = new UserDto();

    @Test
    public void createNewCart() {
        //Given
        //When
        Long cartId = cartController.createNewCart(userDto);
        //Then
        assertEquals(25L, cartId, 0.001);
    }

    @Test
    public void getProductsFromCart() {
        //Given
        //When
        List<ProductDto> productDtoList = cartController.getProductsFromCart(25L);
        //Then
        assertEquals(0, productDtoList.size());
    }

    @Test
    public void addProductToCart() {
        //Given
        //When
        CartDto cartDto = cartController.addProductToCart(25L, 25L);
        //Then
        assertEquals(25L, cartDto.getCartId(), 0.001);
    }
}