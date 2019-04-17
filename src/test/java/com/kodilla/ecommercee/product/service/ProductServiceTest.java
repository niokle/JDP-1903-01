package com.kodilla.ecommercee.product.service;

import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    ProductService productService;
    
    @Autowired
    ProductRepository productRepository;
    

    @Test
    public void findProducts() {
        //given
        Product product1 = new Product("skarpety", "skarpety jedna zielona i jedna czerwona", 100.00, 1L, 1L);
        Product product2 = new Product("kurtka zimowa", "kurtka puchowa zielona", 200.00, 2L, 2L);
        Product product3 = new Product("spodnie", "spodnie w żółte pasy", 300.00, 3L, 3L);
        Product product4 = new Product("bluza", "bluza czerwona puchowa", 400.00, 4L, 4L);
        Product product5 = new Product("bluza z kapturem", "bluza zielona pikowana", 500.00, 5L, 5L);
        Product product6 = new Product("kurtka letnia", "kurtka w żółte rąby pikowana", 600.00, 6L, 6L);
        
        //when
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);

        String searchName1 = "kurtka";
        List<Product> products1 = productService.findProducts(new ProductsSearchParameters(null, searchName1, null, null, null));
        int searchResult1 = 2;
        String searchDescription2 = "zielona";
        List<Product> products2 = productService.findProducts(new ProductsSearchParameters(null, null, searchDescription2, null, null));
        int searchResult2 = 3;
        Double searchPriceMin3 = 199.99;
        Double searchPriceMax3 = 523.30;
        List<Product> products3 = productService.findProducts(new ProductsSearchParameters(null, null, null, searchPriceMin3, searchPriceMax3));
        int searchResult3 = 4;
        String searchName4 = "bluza";
        String searchDescription4 = "pikowana";
        List<Product> products4 = productService.findProducts(new ProductsSearchParameters(null, searchName4, searchDescription4, null, null));
        int searchResult4 = 1;
        String searchDescription5 = "żółte";
        Double searchPriceMin5 = 300.01;
        Double searchPriceMax5 = 600.00;
        List<Product> products5 = productService.findProducts(new ProductsSearchParameters(null, null, searchDescription5, searchPriceMin5, searchPriceMax5));
        int searchResult5 = 1;
        String searchName6 = "ble ble ble";
        List<Product> products6 = productService.findProducts(new ProductsSearchParameters(null, searchName6, null, null, null));
        int searchResult6 = 0;
        String searchDescription7 = "żółte";
        Double searchPriceMax7 = 400.00;
        List<Product> products7 = productService.findProducts(new ProductsSearchParameters(null, null, searchDescription7, null, searchPriceMax7));
        int searchResult7 = 1;
        Double searchPriceMax8 = 400.00;
        List<Product> products8 = productService.findProducts(new ProductsSearchParameters(null, null, null, null, searchPriceMax8));
        int searchResult8 = 4;

        //then
        Assert.assertEquals(searchResult1, products1.size());
        Assert.assertEquals(searchResult2, products2.size());
        Assert.assertEquals(searchResult3, products3.size());
        Assert.assertEquals(searchResult4, products4.size());
        Assert.assertEquals(searchResult5, products5.size());
        Assert.assertEquals(searchResult6, products6.size());
        Assert.assertEquals(searchResult7, products7.size());
        Assert.assertEquals(searchResult8, products8.size());

        //cleanup
        productRepository.delete(product1);
        productRepository.delete(product2);
        productRepository.delete(product3);
        productRepository.delete(product4);
        productRepository.delete(product5);
        productRepository.delete(product6);
    }
}