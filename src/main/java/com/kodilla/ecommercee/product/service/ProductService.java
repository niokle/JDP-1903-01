package com.kodilla.ecommercee.product.service;

import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findProducts(Long id, String partOfName, String partOfDescription, Double priceMin, Double priceMax) {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .filter(product -> filterFunction(product, id, partOfName, partOfDescription, priceMin, priceMax))
                .collect(Collectors.toList());
    }

    private static boolean filterFunction(Product product, Long id, String partOfName, String partOfDescription, Double priceMin, Double priceMax) {
        boolean idResult;
        boolean partOfNameResult;
        boolean partOfDescriptionResult;
        boolean priceMinResult;
        boolean priceMaxResult;

        if (id == null) {
            idResult = true;
        } else {
            idResult = product.getId() == id;
        }

        if (partOfName == null) {
            partOfNameResult = true;
        } else {
            partOfNameResult = product.getName().contains(partOfName);
        }

        if (partOfDescription == null) {
            partOfDescriptionResult = true;
        } else {
            partOfDescriptionResult = product.getDescription().contains(partOfDescription);
        }

        if (priceMin == null && priceMax == null) {
            priceMinResult = true;
            priceMaxResult = true;
        } else if (priceMin != null && priceMax != null) {
            priceMinResult = product.getPrice() >= priceMin;
            priceMaxResult = product.getPrice() <= priceMax;
        } else {
            if (priceMin == null) {
                priceMinResult = true;
            } else {
                priceMinResult = product.getPrice() >= priceMin;
            }
            if (priceMax == null) {
                priceMaxResult = true;
            } else {
                priceMaxResult = product.getPrice() >= priceMax;
            }
        }
        return idResult && partOfNameResult && partOfDescriptionResult && priceMinResult && priceMaxResult;
    }
}
