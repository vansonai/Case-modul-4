package com.example.casemodul4.controller;

import com.example.casemodul4.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping("/customers/create")
    public ModelAndView formCreateCustomer(){
        ModelAndView modelAndView = new ModelAndView("/customer/form");
        return modelAndView;
    }
    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("/home");
        return modelAndView;
    }

}
