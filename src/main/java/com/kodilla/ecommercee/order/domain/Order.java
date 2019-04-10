package com.kodilla.ecommercee.order.domain;


import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.product.domain.Product;

import lombok.*;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_description")
    private String orderDescription;

    @ManyToMany(
            targetEntity = Product.class,
            mappedBy = "orderList",
            fetch = FetchType.EAGER
    )
    private List<Product> productList = new ArrayList<>();
}

