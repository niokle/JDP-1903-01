package com.kodilla.ecommercee.product.controller;

import com.kodilla.ecommercee.product.dto.ProductDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ProductControllerTest {

    private ProductController productController = new ProductController();

    @Test
    public void testGetProducts() {
        //given

        //when
        List<ProductDto> products = productController.getProducts();

        //then
        Assert.assertEquals(0, products.size());
    }

    @Test
    public void testGetProduct() {
        //given

        //when
        ProductDto productDto = productController.getProduct(1);

        //then
        Assert.assertEquals(null, productDto.getPrice());
        Assert.assertEquals(null, productDto.getDescription());
        Assert.assertEquals(null, productDto.getGroupId());
        Assert.assertEquals(null, productDto.getName());
        Assert.assertEquals(null, productDto.getId());
    }
}