package com.kodilla.ecommercee.product.service;

import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kodilla.ecommercee.product.service.FindProductsFunctions.*;

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

    public List<Product> findProducts(FindProductsParameters findProductsParameters) {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .filter(product -> isId(product, findProductsParameters.getId()))
                .filter(product -> isPartOfName(product, findProductsParameters.getPartOfName()))
                .filter(product -> isPartOfDescription(product, findProductsParameters.getPartOfDescription()))
                .filter(product -> isPriceMin(product, findProductsParameters.getPriceMin()))
                .filter(product -> isPriceMax(product, findProductsParameters.getPriceMax()))
                .collect(Collectors.toList());
    }
}
