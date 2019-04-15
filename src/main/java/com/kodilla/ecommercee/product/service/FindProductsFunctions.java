package com.kodilla.ecommercee.product.service;

import com.kodilla.ecommercee.product.domain.Product;

public class FindProductsFunctions {
    public static boolean isId(Product product, Long id) {
        boolean result = true;
        if (id != null) {
            result = product.getId() == id;
        }
        return result;
    }

    public static boolean isPartOfName(Product product, String partOfName) {
        boolean result = true;
        if (partOfName != null) {
            result = product.getName().contains(partOfName);
        }
        return result;
    }

    public static boolean isPartOfDescription(Product product, String partOfDescription) {
        boolean result = true;
        if (partOfDescription != null) {
            result = product.getDescription().contains(partOfDescription);
        }
        return result;
    }

    public static boolean isPriceMin(Product product, Double priceMin) {
        boolean result = true;
        if (priceMin != null) {
            result = product.getPrice() >= priceMin;
        }
        return result;
    }

    public static boolean isPriceMax(Product product, Double priceMax) {
        boolean result = true;
        if (priceMax != null) {
            result = product.getPrice() <= priceMax;
        }
        return result;
    }
}
