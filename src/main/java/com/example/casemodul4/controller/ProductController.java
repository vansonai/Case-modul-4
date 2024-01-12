package com.example.casemodul4.controller;

import com.example.casemodul4.model.Product;
import com.example.casemodul4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @GetMapping
    public ModelAndView findAll(@PageableDefault Pageable pageable){
        return new ModelAndView("/product/list", "products", iProductService.findAll(pageable));
    }
    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable Long id){
        return new ModelAndView("/product/form", "product", iProductService.findById(id));
    }
    @GetMapping("/create")
    public ModelAndView create(){
        return new ModelAndView("/product/form", "product", new Product());
    }
    @PostMapping
    public String save(@RequestBody Product product){
        iProductService.save(product);
        return "redirect:/products";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        iProductService.delete(id);
        return "redirect:/products";
    }
}
