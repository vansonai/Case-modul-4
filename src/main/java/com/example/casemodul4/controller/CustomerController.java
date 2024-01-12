package com.example.casemodul4.controller;

import com.example.casemodul4.model.Customer;
import com.example.casemodul4.service.CustomerService;
import com.example.casemodul4.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping("/list")
    public ModelAndView listCustomer(){
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customerService.findAll());
        return modelAndView;
    }
    @GetMapping("/update/{id}")
    public ModelAndView updateCustomer(@PathVariable Long id){
        Optional<Customer> customer = customerService.findById(id);

        if (customer.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/customer/update");
            modelAndView.addObject("customers", customer.get());
            return modelAndView;
        }else {
            return new ModelAndView("/error_404");
        }
    }
}
