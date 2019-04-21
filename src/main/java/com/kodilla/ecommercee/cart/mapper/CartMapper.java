package com.kodilla.ecommercee.cart.mapper;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.dto.CartDto;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.dto.ProductDto;
import com.kodilla.ecommercee.product.mapper.ProductMapper;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.dto.UserDto;
import com.kodilla.ecommercee.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartMapper {
    @Autowired
    private ProductMapper mapper;

    @Autowired
    private UserMapper userMapper;

    public Cart mapToCart(final CartDto cartDto) {
        return new Cart(
                cartDto.getCartId(),
                cartDto.getProductDtoList().stream()
                    .map(productDto -> mapper.mapProductDtoToProduct(productDto))
                    .collect(Collectors.toList()),
                userMapper.mapUserDtoToUser(cartDto.getUserDto())
        );
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(
                cart.getCartId(),
                cart.getProductList().stream()
                        .map(product -> mapper.mapProductToProductDto(product))
                        .collect(Collectors.toList()),
                userMapper.mapUserToUserDto(cart.getUser())
        );
    }
}
