package com.kodilla.ecommercee.cart.controller;

import com.kodilla.ecommercee.cart.dto.CartDto;
import com.kodilla.ecommercee.product.dto.ProductDto;
import com.kodilla.ecommercee.user.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("ecommercee/cart")
public class CartController {
    @PostMapping(value = "createNewCart")
    public Long createNewCart(@RequestBody UserDto user) {
        CartDto cartDto = new CartDto(25L);
        return cartDto.getCartId();
    }

    @GetMapping(value = "getItemsFromCart")
    public List<ProductDto> getProductsFromCart(@RequestParam Long cartId) {
        return new ArrayList<>();
    }

    @PutMapping(value = "addItemToCart")
    public CartDto addProductToCart(@RequestParam Long productId, @RequestParam Long cartId) {
        return new CartDto(cartId);
    }

    @DeleteMapping(value = "deleteItem")
    public void deleteItemFromCart(@RequestParam Long productId, @RequestParam Long cartId) {
        //This method will be deleting Products from Cart
    }

    @PostMapping(value = "createOrder")
    public void createOrder(@RequestParam Long cartId) {
        //This method will be creating new order based upon Cart's current content
    }
}
