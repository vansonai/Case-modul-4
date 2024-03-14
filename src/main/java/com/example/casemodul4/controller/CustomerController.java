package com.example.casemodul4.controller;

import com.example.casemodul4.model.Customer;
import com.example.casemodul4.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView formUpdateCustomer(@PathVariable Long id){
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/customer/update");
            modelAndView.addObject("customers", customer.get());
            return modelAndView;
        }else {
            return new ModelAndView("/error_404");
        }
    }
    @PostMapping("/update")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("customer/update");
        modelAndView.addObject("customers", customer);
        modelAndView.addObject("message", "Customer updated successfully");
        return modelAndView;
    }
}
