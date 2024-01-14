package com.example.casemodul4.controller;

import com.example.casemodul4.model.Category;
import com.example.casemodul4.model.Product;
import com.example.casemodul4.service.ICategoryService;
import com.example.casemodul4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICategoryService iCategoryService;
    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return iCategoryService.findAll();
    }
    @GetMapping("")
    public String listProducts(Model model){
        Iterable<Product> products = iProductService.findAll();
        model.addAttribute("products", products);
        return "/product/list";
    }
    @GetMapping("create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @PostMapping("create")
    public ModelAndView saveProduct(@ModelAttribute ("product") Product product){
        iProductService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @GetMapping("edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Optional<Product> productOptional = iProductService.findById(id);
        if(productOptional.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", productOptional.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }
    @PostMapping("edit")
    public ModelAndView editProduct(@ModelAttribute("product") Product product){
        iProductService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", product);
        return modelAndView;
    }
    @GetMapping("delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Optional<Product> product = iProductService.findById(id);
        if(product.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }
    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product){
        iProductService.remove(product.getId());
        return "redirect:/products";
    }
}
