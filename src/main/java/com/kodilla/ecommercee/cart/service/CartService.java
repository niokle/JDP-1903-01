package com.kodilla.ecommercee.cart.service;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.exception.CartNotFoundException;
import com.kodilla.ecommercee.cart.repository.CartRepository;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderRepository;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.exception.ProductNotFoundException;
import com.kodilla.ecommercee.product.repository.ProductRepository;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.exception.UserNotFoundException;
import com.kodilla.ecommercee.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Long createCart(Long userId) throws UserNotFoundException {
        Cart cart = new Cart();
        cart.setUser(userRepository.findById(userId).orElseThrow(UserNotFoundException::new));
        cartRepository.save(cart);
        return cart.getCartId();
    }

    public List<Product> getProducts(Long cartId) throws CartNotFoundException {
        Cart cart = findCartUsingId(cartId);
        return cart.getProductList();
    }

    public Cart addProductToCart(Long productId, Long cartId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = findCartUsingId(cartId);
        Product product = findProductUsingProductId(productId);
        List<Product> productList = cart.getProductList();
        productList.add(product);
        cart.setProductList(productList);
        cartRepository.save(cart);
        return cart;
    }

    @Transactional
    public Cart deleteItemFromCart(Long productId, Long cartId) throws CartNotFoundException, ProductNotFoundException {
        Cart selectedCart = findCartUsingId(cartId);
        Product selectedProduct = findProductUsingProductId(productId);

        List<Product> productList = new ArrayList<>();
        productList.add(selectedProduct);

        List<Cart> cartList = new ArrayList<>();
        cartList.add(selectedCart);

        selectedCart.getProductList().removeAll(productList);
        selectedProduct.getCartList().removeAll(cartList);

        cartRepository.save(selectedCart);
        productRepository.save(selectedProduct);
        return selectedCart;
    }

    @Transactional
    public void createOrder(Long cartId, String description) throws CartNotFoundException {
        Order order = new Order();
        order.setOrderDescription(description);

        Cart selectedCart = findCartUsingId(cartId);

        List<Product> productList = selectedCart.getProductList();
        productList.forEach(product -> product.getOrderList().add(order));

        order.getProductList().addAll(productList);

        User user = selectedCart.getUser();
        user.getOrders().add(order);

        order.setUser(user);

        userRepository.save(user);
        orderRepository.save(order);
        productRepository.saveAll(productList);
    }

    private Cart findCartUsingId(Long cartId) throws CartNotFoundException {
        return cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
    }

    private Product findProductUsingProductId(Long productId) throws ProductNotFoundException {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }
}
