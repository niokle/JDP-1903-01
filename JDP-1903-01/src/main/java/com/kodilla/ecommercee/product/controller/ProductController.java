package com.kodilla.ecommercee.product.controller;

import com.kodilla.ecommercee.product.dto.ProductDto;
import org.springframework.web.bind.annotation.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("ecommercee/product")
public class ProductController {

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<ProductDto> getProducts() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProduct")
    public ProductDto getProduct(@RequestParam int id) {
        return new ProductDto();
    }

    @RequestMapping(method = RequestMethod.POST, value = "addProduct", consumes = APPLICATION_JSON_VALUE)
    public void addProduct(@RequestBody ProductDto productDto) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct", consumes = APPLICATION_JSON_VALUE)
    public ProductDto editProduct(@RequestBody ProductDto productDto) {
        return new ProductDto();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(@RequestParam int id) {

    }
}


