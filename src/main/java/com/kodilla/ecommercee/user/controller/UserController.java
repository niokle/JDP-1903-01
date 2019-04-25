package com.kodilla.ecommercee.user.controller;


import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.dto.UserDto;
import com.kodilla.ecommercee.user.exception.UserNotFoundException;
import com.kodilla.ecommercee.user.mapper.UserMapper;
import com.kodilla.ecommercee.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("ecommercee/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Long createUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userMapper.mapUserDtoToUser(userDto)).getUserId();
    }

    @PutMapping(path = "/blockUser")
    public Long blockUser(@RequestParam Long userId) throws UserNotFoundException {
        User user = userService.getUser(userId).orElseThrow(UserNotFoundException::new);
        user.setStatus("0");
        return userService.saveUser(user).getUserId();
    }

    @PutMapping(path = "/generateKey")
    public Long generateKey(@RequestParam Long userId) throws UserNotFoundException {
        User user = userService.getUser(userId).orElseThrow(UserNotFoundException::new);
        user.setUserKey((long) new Random().nextInt(100000));
        return userService.saveUser(user).getUserId();
    }

    @GetMapping(path = "/getUser")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return userMapper.mapUserToUserDto(userService.getUser(userId).orElseThrow(UserNotFoundException::new));
    }

    @GetMapping(path = "/getUsers")
    public List<UserDto> getUsers() {
        return userMapper.mapUsersListToProductDtosList(userService.getUsers());
    }
}
