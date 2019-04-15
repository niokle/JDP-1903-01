package com.kodilla.ecommercee.product.repository;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartRepository;
import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.repository.GroupRepository;
import com.kodilla.ecommercee.order.repository.OrderRepository;
import com.kodilla.ecommercee.product.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductEntityCrudOperationsTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    private Logger LOGGER = LoggerFactory.getLogger(ProductEntityCrudOperationsTest.class);


    @Test
    public void shouldCreateProduct() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Product product1 = new Product("Product 1", "This is product 1", 17.99, 34L, null);
        Product product2 = new Product("Product 2", "This is product 2", 23.99, 12L, null);
        Product product3 = new Product("Product 3", "This is product 3", 1.00, 97L, null);

        //When
        long initialProductsCount = productRepository.count();
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        long product2Id = product2.getId();

        //Then
        Assert.assertEquals(initialProductsCount + 3, productRepository.count());
        Assert.assertEquals("This is product 2", productRepository.findById(product2Id).get().getDescription());

        //Cleanup
        productRepository.delete(product1);
        productRepository.delete(product2);
        productRepository.delete(product3);
    }

    @Test
    public void shouldReadProduct() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Product product1 = new Product("Product 1", "This is product 1", 17.99, 34L, null);

        //When
        productRepository.save(product1);
        long product1Id = product1.getId();
        Product productReadFromDB = productRepository.findById(product1Id).get();

        //Then
        Assert.assertEquals("Product 1", productReadFromDB.getName());
        Assert.assertEquals("This is product 1", productReadFromDB.getDescription());
        Assert.assertEquals(17.99, (double) productReadFromDB.getPrice(), 0.005);

        //Cleanup
        productRepository.delete(product1);
    }

    @Test
    public void shouldReadAllProducts() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Product product1 = new Product("Product 1", "This is product 1", 17.99, 34L, null);
        Product product2 = new Product("Product 2", "This is product 2", 23.99, 12L, null);
        Product product3 = new Product("Product 3", "This is product 3", 1.00, 97L, null);


        //When
        long initialProductsCount = productRepository.count();
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        List<Product> productsReadFromDB = productRepository.findAll();

        //Then
        Assert.assertEquals(initialProductsCount + 3, productsReadFromDB.size());

        //Cleanup
        productRepository.delete(product1);
        productRepository.delete(product2);
        productRepository.delete(product3);
    }

    @Test
    public void shouldUpdateProduct() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Product product1 = new Product("Product 1", "This is product 1", 17.99, 34L, null);

        //When
        long initialProductsCount = productRepository.count();
        productRepository.save(product1);
        long product1Id = product1.getId();
        String newProductDescription = "This is product 1, v2.0";
        Product productReadFromDB = productRepository.findById(product1Id).get();
        productReadFromDB.setDescription(newProductDescription);
        productRepository.save(productReadFromDB);

        //Then
        Assert.assertEquals(newProductDescription, productRepository.findById(product1Id).get().getDescription());
        Assert.assertEquals(initialProductsCount + 1, productRepository.count());

        //Cleanup
        productRepository.delete(product1);
    }

    @Test
    public void shouldDeleteProduct() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Group group1 = new Group("Group 1", "This is Group 1");
        Group group2 = new Group("Group 2", "This is Group 2");
        groupRepository.save(group1);
        groupRepository.save(group2);
        Long group1Id = group1.getGroupId();
        Long group2Id = group2.getGroupId();

        Cart cart1 = new Cart();
        Cart cart2 = new Cart();


        Product product1 = new Product("Product 1", "This is product 1", 17.99, 34L, group1Id);
        Product product2 = new Product("Product 2", "This is product 2", 23.99, 12L, group2Id);
        product1.setCartList(null);
        product1.setOrderList(null);
        product2.setCartList(null);
        product2.setOrderList(null);








    }

}
