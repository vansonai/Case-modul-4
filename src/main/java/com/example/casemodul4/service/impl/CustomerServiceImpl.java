package com.example.casemodul4.service.impl;

import com.example.casemodul4.model.Customer;
import com.example.casemodul4.repository.ICustomerRepository;
import com.example.casemodul4.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
