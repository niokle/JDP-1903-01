package com.kodilla.ecommercee.user.mapper;

import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto mapUserToUserDto(final User user) {
        return new UserDto(user.getUserId(),
                user.getUserName(),
                user.getStatus(),
                user.getUserKey());
    }

    public User mapUserDtoToUser(final UserDto userDto) {
        return new User(userDto.getUserName(),
                userDto.getStatus(),
                userDto.getUserKey());
    }

    public List<UserDto> mapUsersListToProductDtosList(final List<User> users) {
        return users.stream()
                .map(user -> new UserDto(user.getUserId(),
                        user.getUserName(),
                        user.getStatus(),
                        user.getUserKey()))
                .collect(Collectors.toList());
    }
}
