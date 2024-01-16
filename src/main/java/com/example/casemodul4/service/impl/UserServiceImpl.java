package com.example.casemodul4.service.impl;

import com.example.casemodul4.dto.UserDto;
import com.example.casemodul4.model.Role;
import com.example.casemodul4.model.Users;
import com.example.casemodul4.repository.IRoleRepository;
import com.example.casemodul4.repository.IUserRepository;
import com.example.casemodul4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(UserDto userDto) {
        Users users = new Users();
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        users.setUsername(userDto.getUsername());
        users.setPassword(encodedPassword);
        Role role = roleRepository.findByName("ROLE_CUSTOMER");
        users.setRoles(Arrays.asList(role));
        userRepository.save(users);
    }

    @Override
    public Users findUserByUsername(String username) {
        return null;
    }

    @Override
    public List<UserDto> findAllUsers() {
        return null;
    }

    @Override
    public Iterable<Users> findAll() {
        return null;
    }

    @Override
    public Optional<Users> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Users users) {

    }

    @Override
    public void remove(Long id) {

    }
}
