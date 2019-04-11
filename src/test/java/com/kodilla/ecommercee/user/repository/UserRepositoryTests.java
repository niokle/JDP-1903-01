package com.kodilla.ecommercee.user.repository;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartRepository;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderRepository;
import com.kodilla.ecommercee.user.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderRepository orderRepository;

    private Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTests.class);

    @Test
    public void testSaveOne() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        User johnSmith = new User();
        johnSmith.setUserName("johnsmith");
        johnSmith.setStatus("1");
        johnSmith.setUserKey(12345L);

        //When
        userRepository.save(johnSmith);
        long johnSmithId = johnSmith.getUserId();

        //Then
        Assert.assertNotEquals(0, johnSmithId);

        //Cleanup
        try {
            userRepository.delete(johnSmith);
            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testSaveThree() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        User johnSmith = new User();
        johnSmith.setUserName("johnsmith");
        johnSmith.setStatus("1");
        johnSmith.setUserKey(12345L);

        User janeDoe = new User();
        janeDoe.setUserName("janedoe");
        janeDoe.setStatus("1");
        janeDoe.setUserKey(54321L);

        User clarkKent = new User();
        clarkKent.setUserName("clarkkent");
        clarkKent.setStatus("1");
        clarkKent.setUserKey(67890L);

        //When
        userRepository.save(johnSmith);
        userRepository.save(janeDoe);
        userRepository.save(clarkKent);
        long userCount = userRepository.count();

        //Then
        Assert.assertEquals(3, userCount);

        //Cleanup
        try {
            userRepository.delete(johnSmith);
            userRepository.delete(janeDoe);
            userRepository.delete(clarkKent);
            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testFindAll() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        User johnSmith = new User();
        johnSmith.setUserName("johnsmith");
        johnSmith.setStatus("1");
        johnSmith.setUserKey(12345L);

        User janeDoe = new User();
        janeDoe.setUserName("janedoe");
        janeDoe.setStatus("1");
        janeDoe.setUserKey(54321L);

        User clarkKent = new User();
        clarkKent.setUserName("clarkkent");
        clarkKent.setStatus("1");
        clarkKent.setUserKey(67890L);

        //When
        userRepository.save(johnSmith);
        userRepository.save(janeDoe);
        userRepository.save(clarkKent);
        List<User> users = userRepository.findAll();

        //Then
        Assert.assertEquals(3, users.size());

        //Cleanup
        try {
            userRepository.deleteAll(users);
            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }


    @Test
    public void testFindById() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        User johnSmith = new User();
        johnSmith.setUserName("johnsmith");
        johnSmith.setStatus("1");
        johnSmith.setUserKey(12345L);

        User janeDoe = new User();
        janeDoe.setUserName("janedoe");
        janeDoe.setStatus("1");
        janeDoe.setUserKey(54321L);

        User clarkKent = new User();
        clarkKent.setUserName("clarkkent");
        clarkKent.setStatus("1");
        clarkKent.setUserKey(67890L);

        //When
        userRepository.save(johnSmith);
        userRepository.save(janeDoe);
        userRepository.save(clarkKent);
        Long johnSmithId = johnSmith.getUserId();
        Long janeDoeId = janeDoe.getUserId();
        Long clarkKentId = clarkKent.getUserId();

        User foundUser1 = userRepository.findById(johnSmithId).get();
        User foundUser2 = userRepository.findById(janeDoeId).get();
        User foundUser3 = userRepository.findById(clarkKentId).get();

        //Then
        Assert.assertEquals("johnsmith", foundUser1.getUserName());
        Assert.assertEquals("janedoe", foundUser2.getUserName());
        Assert.assertEquals("clarkkent", foundUser3.getUserName());

        //Cleanup
        try {
            userRepository.delete(johnSmith);
            userRepository.delete(janeDoe);
            userRepository.delete(clarkKent);
            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testSaveUserWithCarts() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        List<Cart> carts = new ArrayList<>();
        carts.add(cart1);
        carts.add(cart2);

        User johnSmith = new User();
        johnSmith.setUserName("johnSmith");
        johnSmith.setStatus("1");
        johnSmith.setUserKey(12345L);
        johnSmith.setCarts(carts);

        cart1.setUser(johnSmith);
        cart2.setUser(johnSmith);

        //When
        userRepository.save(johnSmith);
        Long johnSmithId = johnSmith.getUserId();

        //Then
        Assert.assertEquals(1, userRepository.count());
        Assert.assertEquals(2, userRepository.findAll().get(0).getCarts().size());

        //CleanUp
        try {

            cartRepository.delete(cart1);
            cartRepository.delete(cart2);
            userRepository.delete(johnSmith);
            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testSaveUserWithOrders() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        Order order1 = new Order();
        Order order2 = new Order();
        orderRepository.save(order1);
        orderRepository.save(order2);

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        User johnSmith = new User();
        johnSmith.setUserName("johnSmith");
        johnSmith.setStatus("1");
        johnSmith.setUserKey(12345L);
        johnSmith.setOrders(orders);

        order1.setUser(johnSmith);
        order2.setUser(johnSmith);

        //When
        userRepository.save(johnSmith);
        Long johnSmithId = johnSmith.getUserId();

        //Then
        Assert.assertEquals(1, userRepository.count());
        Assert.assertEquals(2, userRepository.findAll().get(0).getOrders().size());

        //CleanUp
        try {
            orderRepository.delete(order1);
            orderRepository.delete(order2);
            userRepository.delete(johnSmith);
            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }
}
