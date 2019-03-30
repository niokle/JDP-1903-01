package com.kodilla.ecommercee.cart.controller;

import com.kodilla.ecommercee.cart.domain.CartDto;
import com.kodilla.ecommercee.cart.domain.UserDto;
import com.kodilla.ecommercee.product.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("ecommercee/cart")
public class CartController {
    @RequestMapping(method = RequestMethod.POST, value = "createNewCart")
    public Long createNewCart(@RequestBody UserDto user) {
        CartDto cartDto = new CartDto(25L);
        return cartDto.getCartId();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getItemsFromCart")
    public List<ProductDto> getProductsFromCart(@RequestParam Long cartId) {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addItemToCart")
    public CartDto addProductToCart(@RequestParam Long productId, @RequestParam Long cartId) {
        return new CartDto(cartId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteItem")
    public void deleteItemFromCart(@RequestParam Long productId, @RequestParam Long cartId) {

    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public void createOrder(@RequestParam Long cartId) {

    }
}
