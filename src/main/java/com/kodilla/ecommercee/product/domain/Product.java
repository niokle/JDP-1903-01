package com.kodilla.ecommercee.product.domain;

import com.kodilla.ecommercee.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_price")
    private Double price;

    @Column(name = "group_id")
    private Long groupId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Product(String name, String description, Double price, Long groupId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.groupId = groupId;
    }
}
