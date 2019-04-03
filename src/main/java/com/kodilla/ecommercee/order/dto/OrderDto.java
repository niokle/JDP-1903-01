package com.kodilla.ecommercee.order.dto;

import com.kodilla.ecommercee.product.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDto {
    private Long orderId;
    private String orderDescription;
    private List<ProductDto> productList;
}
