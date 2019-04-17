package com.kodilla.ecommercee.product.repository;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartRepository;
import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.repository.GroupRepository;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderRepository;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.user.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
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
        long productsCountBeforeSave = productRepository.count();

        Group group1 = new Group("Group 1", "This is Group 1");
        Group group2 = new Group("Group 2", "This is Group 2");
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Order order1 = new Order();
        Order order2 = new Order();
        order1.setOrderDescription("Order 1");
        order2.setOrderDescription("Order 2");
        Product product1 = new Product("Product 1", "This is product 1", 17.99, 34L, 1L);
        Product product2 = new Product("Product 2", "This is product 2", 23.99, 12L, 2L);

        cart1.getProductList().add(product1);
        cart1.getProductList().add(product2);
        cart2.getProductList().add(product1);
        order1.getProductList().add(product1);
        order1.getProductList().add(product2);
        order2.getProductList().add(product1);
        group1.getProductList().add(product1);
        group1.getProductList().add(product2);
        group2.getProductList().add(product2);

        product1.getCartList().add(cart1);
        product1.getCartList().add(cart2);
        product2.getCartList().add(cart1);
        product1.getGroupList().add(group1);
        product2.getGroupList().add(group1);
        product2.getGroupList().add(group2);
        product1.getOrderList().add(order1);
        product1.getOrderList().add(order2);
        product2.getOrderList().add(order1);

        groupRepository.save(group1);
        groupRepository.save(group2);
        cartRepository.save(cart1);
        cartRepository.save(cart2);
        orderRepository.save(order1);
        orderRepository.save(order2);
        productRepository.save(product1);
        productRepository.save(product2);

        //When
        long productsCountBeforeDelete = productRepository.count();
        long groupsCountBeforeDelete = groupRepository.count();
        long cartsCountBeforeDelete = cartRepository.count();
        long ordersCountBeforeDelete = orderRepository.count();
        productRepository.delete(product1);
        productRepository.delete(product2);
        long productsCountAfterDelete = productRepository.count();
        long groupsCountAfterDelete = groupRepository.count();
        long cartsCountAfterDelete = cartRepository.count();
        long ordersCountAfterDelete = orderRepository.count();

        //Then
        Assert.assertEquals(productsCountBeforeSave + 2, productsCountBeforeDelete);
        Assert.assertEquals(productsCountBeforeSave, productsCountAfterDelete);
        Assert.assertEquals(groupsCountBeforeDelete, groupsCountAfterDelete);
        Assert.assertEquals(cartsCountBeforeDelete, cartsCountAfterDelete);
        Assert.assertEquals(ordersCountBeforeDelete, ordersCountAfterDelete);

        //cleanup
        groupRepository.delete(group1);
        groupRepository.delete(group2);
        cartRepository.delete(cart1);
        cartRepository.delete(cart2);
        orderRepository.delete(order1);
        orderRepository.delete(order2);
    }

}
