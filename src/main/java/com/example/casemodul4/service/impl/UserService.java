package com.example.casemodul4.service.impl;


import com.example.casemodul4.model.User;
import com.example.casemodul4.repository.IUserRepository;
import com.example.casemodul4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public Iterable<User> findAll() {
        return iUserRepository.findAll();
    }

    @Override
    public void save(User users) {
        iUserRepository.save(users);
    }

    @Override
    public Optional<User> findById(Long id) {
        return iUserRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iUserRepository.deleteById(id);
    }

}
