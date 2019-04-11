package com.kodilla.ecommercee.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.ecommercee.cart.domain.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String userName;

    @Column(name = "user_status")
    private String status;

    @Column(name = "user_key")
    private Long userKey;

    @OneToMany(mappedBy = "user", targetEntity = Cart.class)
    private List<Cart> carts;

    public User(String userName, String status, Long userKey) {
        this.userName = userName;
        this.status = status;
        this.userKey = userKey;
    }
}
