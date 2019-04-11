package com.kodilla.ecommercee.cart.mapper;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.dto.CartDto;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.dto.ProductDto;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartMapper {
    public Cart mapToCart(final CartDto cartDto) {
        return new Cart(
                cartDto.getCartId(),
                cartDto.getProductDtoList().stream()
                    .map(productDto -> new Product(
                            productDto.getName(),
                            productDto.getDescription(),
                            productDto.getPrice(),
                            productDto.getQuantity(),
                            productDto.getGroupId()
                    )).collect(Collectors.toList()),
                new User(
                        cartDto.getUserDto().getUserName(),
                        cartDto.getUserDto().getStatus(),
                        cartDto.getUserDto().getUserKey()
                )
        );
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(
                cart.getCartId(),
                cart.getProductList().stream()
                    .map(product -> new ProductDto(
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getQuantity(),
                            product.getGroupId()
                    )).collect(Collectors.toList()),
                new UserDto(
                        cart.getUser().getUserId(),
                        cart.getUser().getUserName(),
                        cart.getUser().getStatus(),
                        cart.getUser().getUserKey()
                )
        );
    }
}
