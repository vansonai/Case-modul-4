package com.example.casemodul4.controller;

import com.example.casemodul4.model.Product;
import com.example.casemodul4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @GetMapping
    public ModelAndView findAll(@PageableDefault Pageable pageable){
        return new ModelAndView("/product/list", "products", iProductService.findAll(pageable));
    }
}
