package com.kodilla.ecommercee.user.controller;


import com.kodilla.ecommercee.user.dto.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ecommercee/users")
public class UserController {
    @PostMapping
    public Long createUser(@RequestBody String userName) {
        UserDto userDto = new UserDto(1L, userName, "1", 12345L);
        return userDto.getUserId();
    }

    @PutMapping(path = "/blockUser")
    public Long blockUser(@RequestParam Long userId) {
        return userId;
    }

    @GetMapping(path = "/generateKey")
    public Long generateKey(@RequestParam Long userId) {
        return 12345678L;
    }
}
