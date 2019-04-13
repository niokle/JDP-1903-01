package com.kodilla.ecommercee.cart.controller;

import com.kodilla.ecommercee.cart.dto.CartDto;
import com.kodilla.ecommercee.cart.exception.CartNotFoundException;
import com.kodilla.ecommercee.cart.mapper.CartMapper;
import com.kodilla.ecommercee.cart.service.CartService;
import com.kodilla.ecommercee.product.dto.ProductDto;
import com.kodilla.ecommercee.product.exception.ProductNotFoundException;
import com.kodilla.ecommercee.product.mapper.ProductMapper;
import com.kodilla.ecommercee.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("ecommercee/cart")
public class CartController {
    @Autowired
    private CartService service;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @PostMapping(value = "createNewCart")
    public Long createNewCart(@RequestParam Long userId) throws UserNotFoundException {
        return service.createCart(userId);
    }

    @GetMapping(value = "getItemsFromCart")
    public List<ProductDto> getProductsFromCart(@RequestParam Long cartId) throws CartNotFoundException {
        return service.getProducts(cartId).stream()
                .map(product -> productMapper.mapProductToProductDto(product))
                .collect(Collectors.toList());
    }

    @PutMapping(value = "addItemToCart")
    public CartDto addProductToCart(@RequestParam Long productId, @RequestParam Long cartId) throws ProductNotFoundException, CartNotFoundException {
        return cartMapper.mapToCartDto(service.addProductToCart(productId, cartId));
    }

    @DeleteMapping(value = "deleteItem")
    public CartDto deleteItemFromCart(@RequestParam Long productId, @RequestParam Long cartId) throws CartNotFoundException, ProductNotFoundException {
        return cartMapper.mapToCartDto(service.deleteItemFromCart(productId, cartId));
    }

    @PostMapping(value = "createOrder")
    public void createOrder(@RequestParam Long cartId, @RequestParam String description) throws CartNotFoundException{
        service.createOrder(cartId, description);
    }
}
