package com.example.casemodul4.controller;

import com.example.casemodul4.dto.UserDto;
import com.example.casemodul4.model.Product;
import com.example.casemodul4.model.User;
import com.example.casemodul4.repository.IUserRepository;
import com.example.casemodul4.service.IProductService;
import com.example.casemodul4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginAndRegisterController {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService iProductService;
    @GetMapping("/login")
    public ModelAndView formLogin(){
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
    @GetMapping("/home")
    public String showHome(Model model){
        Iterable<Product> products = iProductService.findAll();
        model.addAttribute("products", products);
        return "/index";
    }
    @GetMapping("/search")
    public String searchByName(Model model, @RequestParam String name){
        model.addAttribute("products",iProductService.findProductByNameContaining(name));
        return "/index";
    }
//    @GetMapping("/register")
//    public ModelAndView formRegister(){
//        ModelAndView modelAndView = new ModelAndView("/signup");
//        modelAndView.addObject("user", new User());
//        return modelAndView;
//    }
    @PostMapping("/register")
    public String createAccount(@ModelAttribute("user")UserDto userDto){
        userService.saveUser(userDto);
        return "redirect:/home";
    }
    @GetMapping("/{id}")
    public ModelAndView showFromEditAccount(Long id){
        User user = userRepository.findById(id).orElse(null);
        ModelAndView modelAndView = new ModelAndView("customer/form");
        modelAndView.addObject("user",user);
        return modelAndView;
    }
}
