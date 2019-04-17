package com.kodilla.ecommercee.user.mapper;

import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.dto.UserDto;
import org.springframework.stereotype.Component;

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
}
