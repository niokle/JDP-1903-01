package com.kodilla.ecommercee.order.domain;

import com.kodilla.ecommercee.product.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @NotNull
    private Long orderId;

    @Column(name = "ORDERS_DESCRIPTION")
    private String orderDescription;

    @Column(name = "PRODUCT_LISTS")
    private List<ProductDto> productList;

}
