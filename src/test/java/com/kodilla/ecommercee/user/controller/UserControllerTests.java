package com.kodilla.ecommercee.user.controller;

import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.dto.UserDto;
import com.kodilla.ecommercee.user.exception.UserNotFoundException;
import com.kodilla.ecommercee.user.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests {

    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        // given
        User user1 = new User("User 1", "1", 0L);
        User user2 = new User("User 2", "1", 0L);
        UserDto userDto1 = userController.userMapper.mapUserToUserDto(user1);
        UserDto userDto2 = userController.userMapper.mapUserToUserDto(user2);

        // when
        Long userId1 = userController.createUser(userDto1);
        Long userId2 = userController.createUser(userDto2);

        // then
        Assert.assertEquals(2, userService.getUsers().size());

        //cleanup
        userService.deleteUser(userId1);
        userService.deleteUser(userId2);
    }

    @Test
    public void testBlockUser() {
        // given
        User user = new User("User 1", "1", 0L);
        UserDto userDto = userController.userMapper.mapUserToUserDto(user);

        // when
        Long userId = userController.createUser(userDto);
        try {
            userController.blockUser(userId);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        // then
        Assert.assertEquals("0", userService.getUser(userId).get().getStatus());

        //cleanup
        userService.deleteUser(userId);
    }


    @Test
    public void testGenerateKey() {
        // given
        User user = new User("User 1", "1", 0L);
        UserDto userDto = userController.userMapper.mapUserToUserDto(user);

        // when
        Long userId = userController.createUser(userDto);
        try {
            userController.generateKey(userId);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        long userKey = userService.getUser(userId).get().getUserKey();

        // then
        Assert.assertNotEquals(0L, userKey);

        //cleanup
        userService.deleteUser(userId);
    }

    @Test
    public void testGetUser() {
        // given
        User user = new User("User 1", "1", 0L);
        UserDto userDto = userController.userMapper.mapUserToUserDto(user);

        // when
        Long userId = userController.createUser(userDto);
        try {
            userController.getUser(userId);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        // then
        Assert.assertEquals("User 1", userService.getUser(userId).get().getUserName());

        //cleanup
        userService.deleteUser(userId);
    }

    @Test
    public void testGetUsers() {
        // given
        User user1 = new User("User 1", "1", 0L);
        User user2 = new User("User 2", "1", 0L);
        UserDto userDto1 = userController.userMapper.mapUserToUserDto(user1);
        UserDto userDto2 = userController.userMapper.mapUserToUserDto(user2);

        // when
        Long userId1 = userController.createUser(userDto1);
        Long userId2 = userController.createUser(userDto2);

        // then
        Assert.assertEquals(2, userController.getUsers().size());

        //cleanup
        userService.deleteUser(userId1);
        userService.deleteUser(userId2);
    }

}
