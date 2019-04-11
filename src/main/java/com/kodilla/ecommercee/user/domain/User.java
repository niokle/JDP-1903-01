package com.kodilla.ecommercee.user.domain;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String userName;

    @Column(name = "status")
    private String status;

    @Column(name = "user_key")
    private Long userKey;

    @OneToMany(targetEntity = Cart.class,
            mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(targetEntity = Order.class,
            mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    public User(String userName, String status, Long userKey) {
        this.userName = userName;
        this.status = status;
        this.userKey = userKey;
    }


}
