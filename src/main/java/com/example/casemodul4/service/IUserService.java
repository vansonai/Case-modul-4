package com.example.casemodul4.service;

import com.example.casemodul4.dto.UserDto;
import com.example.casemodul4.model.User;

import java.util.List;

public interface IUserService extends IGenerateService<User> {
    void saveUser(UserDto userDto);

    User findUserByUsername(String username);

    List<UserDto> findAllUsers();
}
