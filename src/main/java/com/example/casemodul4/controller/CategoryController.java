package com.example.casemodul4.controller;

import com.example.casemodul4.model.Category;
import com.example.casemodul4.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;
    @GetMapping
    public ModelAndView findAll(@PageableDefault Pageable pageable){
        return new ModelAndView("/category/list", "categories", iCategoryService.findAll(pageable));
    }
    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable Long id){
        return new ModelAndView("/category/form", "category", iCategoryService.findById(id));
    }
    @GetMapping("/create")
    public ModelAndView create(){
        return new ModelAndView("/category/form","product", new Category());
    }
    @PostMapping
    public String save(@RequestBody Category category){
        iCategoryService.save(category);
        return "redirect:/categories";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        iCategoryService.delete(id);
        return "redirect:/categories";
    }
}
