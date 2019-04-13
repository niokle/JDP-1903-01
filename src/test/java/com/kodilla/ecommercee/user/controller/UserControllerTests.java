package com.kodilla.ecommercee.user.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests {

    @Autowired
    private UserController userController;

    @Test
    public void testCreateUser() {
        // given
        // when
        long result = userController.createUser("James Bond");
        // then
        Assert.assertEquals(1, result);
    }

    @Test
    public void testGenerateKey() {
        // given
        // when
        long key = userController.generateKey(23L);
        // then
        Assert.assertEquals(12345678L, key);

    }

}
