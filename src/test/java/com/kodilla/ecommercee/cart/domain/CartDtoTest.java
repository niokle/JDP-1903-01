package com.kodilla.ecommercee.cart.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class CartDtoTest {
    private CartDto cart = new CartDto();

    @Test
    public void getCartId() {
        assertEquals(null, cart.getCartId());
    }

    @Test
    public void getProductDtoList() {
        assertEquals(null, cart.getProductDtoList());
    }

    @Test
    public void getUserDto() {
        assertEquals(null, cart.getProductDtoList());
    }
}