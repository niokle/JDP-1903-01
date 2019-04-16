package com.kodilla.ecommercee.cart.service;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.exception.CartNotFoundException;
import com.kodilla.ecommercee.cart.repository.CartRepository;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderRepository;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.exception.ProductNotFoundException;
import com.kodilla.ecommercee.product.repository.ProductRepository;
import com.kodilla.ecommercee.user.exception.UserNotFoundException;
import com.kodilla.ecommercee.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

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
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        return cart.getProductList();
    }

    public Cart addProductToCart(Long productId, Long cartId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        List<Product> productList = cart.getProductList();
        productList.add(product);
        cart.setProductList(productList);
        cartRepository.save(cart);
        return cart;
    }

    public Cart deleteItemFromCart(Long productId, Long cartId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        List<Product> productList = cart.getProductList();
        List<Cart> cartList = product.getCartList();
        cartList.remove(cart);
        productList.remove(product);
        cart.setProductList(productList);
        product.setCartList(cartList);
        cartRepository.save(cart);
        productRepository.save(product);
        return cart;
    }

    public void createOrder(Long cartId, String description) throws CartNotFoundException {
        Order order = new Order();
        order.setOrderDescription(description);
        order.setProductList(cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new).getProductList());
        order.setUser(cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new).getUser());
        orderRepository.save(order);
    }
}
