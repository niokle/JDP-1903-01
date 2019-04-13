package com.kodilla.ecommercee.product.controller;

import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.dto.ProductDto;
import com.kodilla.ecommercee.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @Autowired
    private ProductService productService;

    @Test
    public void testGetProducts() {
        //given
        int numberOfProductsBeforeAdd = productService.getProducts().size();
        Product product1 = new Product("product 1", "product 1 desc", 100.00, 1L, 1L);
        Product product2 = new Product("product 2", "product 2 desc", 200.00, 2L, 2L);
        productService.saveProduct(product1);
        productService.saveProduct(product2);
        int numberOfProductsAfterAdd = productService.getProducts().size();

        //when
        List<ProductDto> products = productController.getProducts();

        //then
        Assert.assertEquals(numberOfProductsBeforeAdd + 2, numberOfProductsAfterAdd);
        Assert.assertEquals(numberOfProductsAfterAdd, products.size());

        //cleanup
        productService.deleteProduct(product1.getId());
        productService.deleteProduct(product2.getId());
    }

    @Test
    public void testGetProduct() throws Exception {
        //given
        Product product1 = new Product("product 1", "product 1 desc", 100.00, 1L, 1L);
        Product product2 = new Product("product 2", "product 2 desc", 200.00, 2L, 2L);
        productService.saveProduct(product1);
        productService.saveProduct(product2);

        //when
        Product productFromController1 = productController.productMapper.mapProductDtoToProduct(productController.getProduct(product1.getId()));
        Product productFromController2 = productController.productMapper.mapProductDtoToProduct(productController.getProduct(product2.getId()));

        //then
        Assert.assertEquals("product 1", productFromController1.getName());
        Assert.assertEquals("product 2", productFromController2.getName());

        //cleanup
        productService.deleteProduct(product1.getId());
        productService.deleteProduct(product2.getId());
    }

    @Test
    public void testAddProduct() {
        //given
        Product product1 = new Product("product 1", "product 1 desc", 100.00, 1L, 1L);
        Product product2 = new Product("product 2", "product 2 desc", 200.00, 2L, 2L);
        ProductDto productDto1 = productController.productMapper.mapProductToProductDto(product1);
        ProductDto productDto2 = productController.productMapper.mapProductToProductDto(product2);

        //when
        int numberOfProductsBeforeAdd = productService.getProducts().size();
        Long idProduct1 = productController.addProduct(productDto1);
        Long idProduct2 = productController.addProduct(productDto2);
        int numberOfProductsAfterAdd = productService.getProducts().size();

        //then
        Assert.assertEquals(numberOfProductsBeforeAdd + 2, numberOfProductsAfterAdd);

        //cleanup
        productService.deleteProduct(idProduct1);
        productService.deleteProduct(idProduct2);
    }

    @Test
    public void testEditProduct() {
        //given
        Product product1 = new Product("product 1", "product 1 desc", 100.00, 1L, 1L);
        Product product2 = new Product("product 2", "product 2 desc", 200.00, 2L, 2L);
        productService.saveProduct(product1);
        productService.saveProduct(product2);
        ProductDto productDto1 = productController.productMapper.mapProductToProductDto(product1);
        ProductDto productDto2 = productController.productMapper.mapProductToProductDto(product2);

        //when
        String newName1 = "product 3";
        String newName2 = "product 4";
        productDto1.setName(newName1);
        productDto2.setName(newName2);
        productController.editProduct(productDto1);
        productController.editProduct(productDto2);
        String getNewName1 = productService.getProduct(product1.getId()).get().getName();
        String getNewName2 = productService.getProduct(product2.getId()).get().getName();

        //then
        Assert.assertEquals(newName1, getNewName1);
        Assert.assertEquals(newName2, getNewName2);

        //cleanup
        productService.deleteProduct(product1.getId());
        productService.deleteProduct(product2.getId());
    }

    @Test
    public void testDeleteProduct() {
        //given
        int numberOfProductsBeforeAdd = productService.getProducts().size();
        Product product1 = new Product("product 1", "product 1 desc", 100.00, 1L, 1L);
        Product product2 = new Product("product 2", "product 2 desc", 200.00, 2L, 2L);
        productService.saveProduct(product1);
        productService.saveProduct(product2);

        //when
        productController.deleteProduct(product1.getId());
        productController.deleteProduct(product2.getId());

        //then
        Assert.assertEquals(numberOfProductsBeforeAdd, productService.getProducts().size());
    }
}