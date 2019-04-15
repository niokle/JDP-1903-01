package com.kodilla.ecommercee.product.controller;

import com.kodilla.ecommercee.product.dto.ProductDto;
import com.kodilla.ecommercee.product.mapper.ProductMapper;
import com.kodilla.ecommercee.product.service.FindProductsParameters;
import com.kodilla.ecommercee.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("ecommercee/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductMapper productMapper;

    @GetMapping(value = "getProducts")
    public List<ProductDto> getProducts() {
        return productMapper.mapProductsListToProductDtosList(productService.getProducts());
    }

    @GetMapping(value = "getProduct")
    public ProductDto getProduct(@RequestParam Long id) throws Exception {
        return productMapper.mapProductToProductDto(productService.getProduct(id).orElseThrow(Exception::new));
    }

    @PostMapping(value = "addProduct", consumes = APPLICATION_JSON_VALUE)
    public Long addProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(productMapper.mapProductDtoToProduct(productDto)).getId();
    }

    @PutMapping(value = "updateProduct", consumes = APPLICATION_JSON_VALUE)
    public ProductDto editProduct(@RequestBody ProductDto productDto) {
        return productMapper.mapProductToProductDto(productService.saveProduct(productMapper.mapProductDtoToProduct(productDto)));
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping(value = "findProducts", consumes = APPLICATION_JSON_VALUE)
    public List<ProductDto> findProducts(@RequestBody FindProductsParameters findProductsParameters) {
        return productMapper.mapProductsListToProductDtosList(productService.findProducts(findProductsParameters.getId(),
                findProductsParameters.getPartOfName(), findProductsParameters.getPartOfDescription(),
                findProductsParameters.getPriceMin(), findProductsParameters.getPriceMax()));
    }
}


