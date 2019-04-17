package com.kodilla.ecommercee.product.service;

import com.kodilla.ecommercee.product.domain.Product;

public class ProductsSearchFunctions {
    public static boolean hasId(Product product, Long id) {
        boolean result = true;
        if (id != null) {
            result = product.getId() == id;
        }
        return result;
    }

    public static boolean containsName(Product product, String partOfName) {
        boolean result = true;
        if (partOfName != null) {
            result = product.getName().toLowerCase().contains(partOfName.toLowerCase());
        }
        return result;
    }

    public static boolean containsDescription(Product product, String partOfDescription) {
        boolean result = true;
        if (partOfDescription != null) {
            result = product.getDescription().toLowerCase().contains(partOfDescription.toLowerCase());
        }
        return result;
    }

    public static boolean hasPriceMin(Product product, Double priceMin) {
        boolean result = true;
        if (priceMin != null) {
            result = product.getPrice() >= priceMin;
        }
        return result;
    }

    public static boolean hasPriceMax(Product product, Double priceMax) {
        boolean result = true;
        if (priceMax != null) {
            result = product.getPrice() <= priceMax;
        }
        return result;
    }
}
