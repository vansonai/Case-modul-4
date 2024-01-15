package com.example.casemodul4.controller;

import com.example.casemodul4.model.Category;
import com.example.casemodul4.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;
    @GetMapping("")
    public String listCategory(Model model){
        Iterable<Category> categories = iCategoryService.findAll();
        model.addAttribute("categories", categories);
        return "/category/list";
    }
    @GetMapping("create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }
    @PostMapping("create")
    public ModelAndView saveCategory(@ModelAttribute("category") Category category){
        iCategoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }
    @GetMapping("delete/{id}")
    public String deleteCategory(@PathVariable Long id){
        iCategoryService.remove(id);
        return "redirect:/categories";
    }
}
