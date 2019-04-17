package com.kodilla.ecommercee.product.controller;

import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.dto.ProductDto;
import com.kodilla.ecommercee.product.service.ProductsSearchParameters;
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

    @Test
    public void testFindProductsByName() {
        //given
        Product product1 = new Product("skarpety", "skarpety jedna zielona i jedna czerwona", 100.00, 1L, 1L);
        Product product2 = new Product("kurtka zimowa", "kurtka puchowa zielona", 200.00, 2L, 2L);
        Product product3 = new Product("spodnie", "spodnie w żółte pasy", 300.00, 3L, 3L);
        Product product4 = new Product("bluza", "bluza czerwona puchowa", 400.00, 4L, 4L);
        Product product5 = new Product("bluza z kapturem", "bluza zielona pikowana", 500.00, 5L, 5L);
        Product product6 = new Product("kurtka letnia", "kurtka w żółte rąby pikowana", 600.00, 6L, 6L);

        //when
        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);
        productService.saveProduct(product5);
        productService.saveProduct(product6);

        String searchName1 = "kurtka";
        List<ProductDto> productsDto1 = productController.findProducts(new ProductsSearchParameters(null, searchName1, null, null, null));
        int searchResult1 = 2;

        //then
        Assert.assertEquals(searchResult1, productsDto1.size());

        //cleanup
        productService.deleteProduct(product1.getId());
        productService.deleteProduct(product2.getId());
        productService.deleteProduct(product3.getId());
        productService.deleteProduct(product4.getId());
        productService.deleteProduct(product5.getId());
        productService.deleteProduct(product6.getId());
    }

    @Test
    public void testFindProductsByDescription() {
        //given
        Product product1 = new Product("skarpety", "skarpety jedna zielona i jedna czerwona", 100.00, 1L, 1L);
        Product product2 = new Product("kurtka zimowa", "kurtka puchowa zielona", 200.00, 2L, 2L);
        Product product3 = new Product("spodnie", "spodnie w żółte pasy", 300.00, 3L, 3L);
        Product product4 = new Product("bluza", "bluza czerwona puchowa", 400.00, 4L, 4L);
        Product product5 = new Product("bluza z kapturem", "bluza zielona pikowana", 500.00, 5L, 5L);
        Product product6 = new Product("kurtka letnia", "kurtka w żółte rąby pikowana", 600.00, 6L, 6L);

        //when
        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);
        productService.saveProduct(product5);
        productService.saveProduct(product6);

        String searchDescription2 = "zielona";
        List<ProductDto> productsDto2 = productController.findProducts(new ProductsSearchParameters(null, null, searchDescription2, null, null));
        int searchResult2 = 3;

        //then
        Assert.assertEquals(searchResult2, productsDto2.size());

        //cleanup
        productService.deleteProduct(product1.getId());
        productService.deleteProduct(product2.getId());
        productService.deleteProduct(product3.getId());
        productService.deleteProduct(product4.getId());
        productService.deleteProduct(product5.getId());
        productService.deleteProduct(product6.getId());
    }

    @Test
    public void testFindProductsByPriceMinAndMax() {
        //given
        Product product1 = new Product("skarpety", "skarpety jedna zielona i jedna czerwona", 100.00, 1L, 1L);
        Product product2 = new Product("kurtka zimowa", "kurtka puchowa zielona", 200.00, 2L, 2L);
        Product product3 = new Product("spodnie", "spodnie w żółte pasy", 300.00, 3L, 3L);
        Product product4 = new Product("bluza", "bluza czerwona puchowa", 400.00, 4L, 4L);
        Product product5 = new Product("bluza z kapturem", "bluza zielona pikowana", 500.00, 5L, 5L);
        Product product6 = new Product("kurtka letnia", "kurtka w żółte rąby pikowana", 600.00, 6L, 6L);

        //when
        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);
        productService.saveProduct(product5);
        productService.saveProduct(product6);

        Double searchPriceMin3 = 199.99;
        Double searchPriceMax3 = 523.30;
        List<ProductDto> productsDto3 = productController.findProducts(new ProductsSearchParameters(null, null, null, searchPriceMin3, searchPriceMax3));
        int searchResult3 = 4;

        //then
        Assert.assertEquals(searchResult3, productsDto3.size());

        //cleanup
        productService.deleteProduct(product1.getId());
        productService.deleteProduct(product2.getId());
        productService.deleteProduct(product3.getId());
        productService.deleteProduct(product4.getId());
        productService.deleteProduct(product5.getId());
        productService.deleteProduct(product6.getId());
    }

    @Test
    public void testFindProductsByNameAndDescription() {
        //given
        Product product1 = new Product("skarpety", "skarpety jedna zielona i jedna czerwona", 100.00, 1L, 1L);
        Product product2 = new Product("kurtka zimowa", "kurtka puchowa zielona", 200.00, 2L, 2L);
        Product product3 = new Product("spodnie", "spodnie w żółte pasy", 300.00, 3L, 3L);
        Product product4 = new Product("bluza", "bluza czerwona puchowa", 400.00, 4L, 4L);
        Product product5 = new Product("bluza z kapturem", "bluza zielona pikowana", 500.00, 5L, 5L);
        Product product6 = new Product("kurtka letnia", "kurtka w żółte rąby pikowana", 600.00, 6L, 6L);

        //when
        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);
        productService.saveProduct(product5);
        productService.saveProduct(product6);

        String searchName4 = "bluza";
        String searchDescription4 = "pikowana";
        List<ProductDto> productsDto4 = productController.findProducts(new ProductsSearchParameters(null, searchName4, searchDescription4, null, null));
        int searchResult4 = 1;

        //then
        Assert.assertEquals(searchResult4, productsDto4.size());

        //cleanup
        productService.deleteProduct(product1.getId());
        productService.deleteProduct(product2.getId());
        productService.deleteProduct(product3.getId());
        productService.deleteProduct(product4.getId());
        productService.deleteProduct(product5.getId());
        productService.deleteProduct(product6.getId());
    }

    @Test
    public void testFindProductsByDescriptionAndPriceMinAndMax() {
        //given
        Product product1 = new Product("skarpety", "skarpety jedna zielona i jedna czerwona", 100.00, 1L, 1L);
        Product product2 = new Product("kurtka zimowa", "kurtka puchowa zielona", 200.00, 2L, 2L);
        Product product3 = new Product("spodnie", "spodnie w żółte pasy", 300.00, 3L, 3L);
        Product product4 = new Product("bluza", "bluza czerwona puchowa", 400.00, 4L, 4L);
        Product product5 = new Product("bluza z kapturem", "bluza zielona pikowana", 500.00, 5L, 5L);
        Product product6 = new Product("kurtka letnia", "kurtka w żółte rąby pikowana", 600.00, 6L, 6L);

        //when
        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);
        productService.saveProduct(product5);
        productService.saveProduct(product6);

        String searchDescription5 = "żółte";
        Double searchPriceMin5 = 300.01;
        Double searchPriceMax5 = 600.00;
        List<ProductDto> productsDto5 = productController.findProducts(new ProductsSearchParameters(null, null, searchDescription5, searchPriceMin5, searchPriceMax5));
        int searchResult5 = 1;

        //then
        Assert.assertEquals(searchResult5, productsDto5.size());

        //cleanup
        productService.deleteProduct(product1.getId());
        productService.deleteProduct(product2.getId());
        productService.deleteProduct(product3.getId());
        productService.deleteProduct(product4.getId());
        productService.deleteProduct(product5.getId());
        productService.deleteProduct(product6.getId());
    }

    @Test
    public void testFindProductsByNotExistName() {
        //given
        Product product1 = new Product("skarpety", "skarpety jedna zielona i jedna czerwona", 100.00, 1L, 1L);
        Product product2 = new Product("kurtka zimowa", "kurtka puchowa zielona", 200.00, 2L, 2L);
        Product product3 = new Product("spodnie", "spodnie w żółte pasy", 300.00, 3L, 3L);
        Product product4 = new Product("bluza", "bluza czerwona puchowa", 400.00, 4L, 4L);
        Product product5 = new Product("bluza z kapturem", "bluza zielona pikowana", 500.00, 5L, 5L);
        Product product6 = new Product("kurtka letnia", "kurtka w żółte rąby pikowana", 600.00, 6L, 6L);

        //when
        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);
        productService.saveProduct(product5);
        productService.saveProduct(product6);

        String searchName6 = "ble ble ble";
        List<ProductDto> productsDto6 = productController.findProducts(new ProductsSearchParameters(null, searchName6, null, null, null));
        int searchResult6 = 0;

        //then
        Assert.assertEquals(searchResult6, productsDto6.size());

        //cleanup
        productService.deleteProduct(product1.getId());
        productService.deleteProduct(product2.getId());
        productService.deleteProduct(product3.getId());
        productService.deleteProduct(product4.getId());
        productService.deleteProduct(product5.getId());
        productService.deleteProduct(product6.getId());
    }

    @Test
    public void testFindProductsByDescriptionAndPriceMax() {
        //given
        Product product1 = new Product("skarpety", "skarpety jedna zielona i jedna czerwona", 100.00, 1L, 1L);
        Product product2 = new Product("kurtka zimowa", "kurtka puchowa zielona", 200.00, 2L, 2L);
        Product product3 = new Product("spodnie", "spodnie w żółte pasy", 300.00, 3L, 3L);
        Product product4 = new Product("bluza", "bluza czerwona puchowa", 400.00, 4L, 4L);
        Product product5 = new Product("bluza z kapturem", "bluza zielona pikowana", 500.00, 5L, 5L);
        Product product6 = new Product("kurtka letnia", "kurtka w żółte rąby pikowana", 600.00, 6L, 6L);

        //when
        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);
        productService.saveProduct(product5);
        productService.saveProduct(product6);

        String searchDescription7 = "żółte";
        Double searchPriceMax7 = 400.00;
        List<ProductDto> productsDto7 = productController.findProducts(new ProductsSearchParameters(null, null, searchDescription7, null, searchPriceMax7));
        int searchResult7 = 1;

        //then
        Assert.assertEquals(searchResult7, productsDto7.size());

        //cleanup
        productService.deleteProduct(product1.getId());
        productService.deleteProduct(product2.getId());
        productService.deleteProduct(product3.getId());
        productService.deleteProduct(product4.getId());
        productService.deleteProduct(product5.getId());
        productService.deleteProduct(product6.getId());
    }

    @Test
    public void testFindProductsByPriceMax() {
        //given
        Product product1 = new Product("skarpety", "skarpety jedna zielona i jedna czerwona", 100.00, 1L, 1L);
        Product product2 = new Product("kurtka zimowa", "kurtka puchowa zielona", 200.00, 2L, 2L);
        Product product3 = new Product("spodnie", "spodnie w żółte pasy", 300.00, 3L, 3L);
        Product product4 = new Product("bluza", "bluza czerwona puchowa", 400.00, 4L, 4L);
        Product product5 = new Product("bluza z kapturem", "bluza zielona pikowana", 500.00, 5L, 5L);
        Product product6 = new Product("kurtka letnia", "kurtka w żółte rąby pikowana", 600.00, 6L, 6L);

        //when
        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);
        productService.saveProduct(product5);
        productService.saveProduct(product6);

        Double searchPriceMax8 = 400.00;
        List<ProductDto> productsDto8 = productController.findProducts(new ProductsSearchParameters(null, null, null, null, searchPriceMax8));
        int searchResult8 = 4;

        //then
        Assert.assertEquals(searchResult8, productsDto8.size());

        //cleanup
        productService.deleteProduct(product1.getId());
        productService.deleteProduct(product2.getId());
        productService.deleteProduct(product3.getId());
        productService.deleteProduct(product4.getId());
        productService.deleteProduct(product5.getId());
        productService.deleteProduct(product6.getId());
    }

    @Test
    public void testFindProductsByNameAndDescriptionUpperCaseAndLowerCase() {
        //given
        Product product1 = new Product("skarpety", "skarpety jedna zielona i jedna czerwona", 100.00, 1L, 1L);
        Product product2 = new Product("kurtka zimowa", "KURTKA ZIELONA PUCHOWA", 200.00, 2L, 2L);
        Product product3 = new Product("spodnie", "spodnie w żółte pasy", 300.00, 3L, 3L);
        Product product4 = new Product("bluza", "bluza czerwona puchowa", 400.00, 4L, 4L);
        Product product5 = new Product("bluza z kapturem", "bluza zielona pikowana", 500.00, 5L, 5L);
        Product product6 = new Product("kurtka letnia", "KURTKA ŻÓŁTA W RĄBY", 600.00, 6L, 6L);

        //when
        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);
        productService.saveProduct(product5);
        productService.saveProduct(product6);

        String searchName9 = "KuR";
        String searchDescription9 = "kUr";
        List<ProductDto> productsDto9 = productController.findProducts(new ProductsSearchParameters(null, searchName9, searchDescription9, null, null));
        int searchResult9 = 2;

        //then
        Assert.assertEquals(searchResult9, productsDto9.size());

        //cleanup
        productService.deleteProduct(product1.getId());
        productService.deleteProduct(product2.getId());
        productService.deleteProduct(product3.getId());
        productService.deleteProduct(product4.getId());
        productService.deleteProduct(product5.getId());
        productService.deleteProduct(product6.getId());
    }
}