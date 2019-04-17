package com.kodilla.ecommercee.product.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductsSearchParameters {
    private Long id;
    private String partOfName;
    private String partOfDescription;
    private Double priceMin;
    private Double priceMax;
}
