package com.kodilla.ecommercee.cart.service;

import com.kodilla.ecommercee.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
}
