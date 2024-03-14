package com.example.casemodul4.service.impl;

import com.example.casemodul4.dto.UserDto;
import com.example.casemodul4.model.Role;
import com.example.casemodul4.model.User;
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
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(UserDto userDto) {
        User users = new User();
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        users.setUsername(userDto.getUsername());
        users.setPassword(encodedPassword);
        Role role = roleRepository.findByName("ROLE_CUSTOMER");
        users.setRoles(Arrays.asList(role));
        userRepository.save(users);
    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public List<UserDto> findAllUsers() {
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User users) {

    }

    @Override
    public void remove(Long id) {

    }
}
