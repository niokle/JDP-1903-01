package com.kodilla.ecommercee.product.domain;

import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.order.domain.Order;
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

    @Column(name = "product_quantity")
    private Long quantity;

    @Column(name = "group_id")
    private Long groupId;

    @ManyToMany
    @JoinTable(
            name = "join_groups_products",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "group_id")}
    )
    private List<Group> groupList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "join_carts_products",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "cart_id")}
    )
    private List<Cart> cartList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "join_orders_products",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "order_id")}
    )
    private List<Order> orderList = new ArrayList<>();

    public Product(String name, String description, Double price, Long quantity, Long groupId) {

            this.name = name;
            this.description = description;
            this.price = price;
            this.quantity = quantity;
            this.groupId = groupId;
    }

    public Product(Long id, String name, String description, Double price, Long quantity, Long groupId) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
            this.quantity = quantity;
            this.groupId = groupId;
    }
}
