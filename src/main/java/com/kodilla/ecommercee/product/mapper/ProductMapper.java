package com.kodilla.ecommercee.product.mapper;

import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public Product mapProductDtoToProduct(final ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getName(), productDto.getDescription(), productDto.getPrice(),
                productDto.getQuantity(), productDto.getGroupId());
    }

    public ProductDto mapProductToProductDto(final Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(),
                product.getQuantity(), product.getGroupId());
    }

    public List<ProductDto> mapProductsListToProductDtosList(final List<Product> products) {
        return products.stream()
                .map(product -> new ProductDto(product.getId(), product.getName(), product.getDescription(),
                        product.getPrice(), product.getQuantity(), product.getGroupId()))
                .collect(Collectors.toList());
    }
}
