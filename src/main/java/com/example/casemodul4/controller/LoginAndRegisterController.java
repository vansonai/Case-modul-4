package com.example.casemodul4.controller;

import com.example.casemodul4.dto.UserDto;
import com.example.casemodul4.model.Users;
import com.example.casemodul4.repository.IUserRepository;
import com.example.casemodul4.security.UserPrinciple;
import com.example.casemodul4.security.service.UserInfoService;
import com.example.casemodul4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginAndRegisterController {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserService userService;
    @GetMapping("/login-register")
    public ModelAndView formLogin(){
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("user", new Users());
        return modelAndView;
    }
    @GetMapping("/home")
    public String showHome(){
        return "/product/list";
    }
//    @GetMapping("/register")
//    public ModelAndView formRegister(){
//        ModelAndView modelAndView = new ModelAndView("/login");
//        modelAndView.addObject("user", new Users());
//        return modelAndView;
//    }
    @PostMapping("/register")
    public String createAccount(@ModelAttribute("user")UserDto userDto){
        userService.saveUser(userDto);
        return "redirect:/home";
    }
    @GetMapping("/{id}")
    public ModelAndView showFromEditAccount(Long id){
        Users user = userRepository.findById(id).orElse(null);
        ModelAndView modelAndView = new ModelAndView("customer/form");
        modelAndView.addObject("user",user);
        return modelAndView;
    }
}
