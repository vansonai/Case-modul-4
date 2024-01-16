package com.example.casemodul4.service;

import com.example.casemodul4.dto.UserDto;
import com.example.casemodul4.model.Users;

import java.util.List;

public interface IUserService extends IGenerateService<Users> {
    void saveUser(UserDto userDto);

    Users findUserByUsername(String username);

    List<UserDto> findAllUsers();
}
