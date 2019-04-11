package com.kodilla.ecommercee.cart.dto;
import com.kodilla.ecommercee.product.dto.ProductDto;

import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartDto {
    private Long cartId;
    private List<ProductDto> productDtoList;
    private UserDto userDto;

    public CartDto(Long cartId) {
        this.cartId = cartId;
    }

}
