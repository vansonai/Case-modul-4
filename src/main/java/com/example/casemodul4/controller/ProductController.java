package com.example.casemodul4.controller;

import com.example.casemodul4.model.Cart;
import com.example.casemodul4.model.Category;
import com.example.casemodul4.model.Product;
import com.example.casemodul4.service.ICategoryService;
import com.example.casemodul4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@SessionAttributes("cart")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICategoryService iCategoryService;
    @ModelAttribute("cart")
    public Cart setUpCart(){
        return new Cart();
    }
    @Value("$={file-upload}")
    private String upload;
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

        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        modelAndView.addObject("product", new Product());

        //Tai file
        MultipartFile file = product.getFile();
        if(file != null && file.getSize() != 0){
            String fileName = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(upload + fileName));
                product.setImage(fileName);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else {
            product.setImage(iProductService.findById(product.getId()).get().getImage());
        }
        iProductService.save(product);
        return modelAndView;
    }
    @GetMapping("update/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id){
        Optional<Product> productOptional = iProductService.findById(id);
        if(productOptional.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/product/update");
            modelAndView.addObject("product", productOptional.get());

            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }
    @PostMapping("update")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product){

        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        modelAndView.addObject("product", product);

        // Kiểm tra và xử lý ảnh mới
//        MultipartFile file = product.getFile();
//        if (file != null && !file.isEmpty()) {
//            String fileName = file.getOriginalFilename();
//            try {
//                // Lưu ảnh mới vào thư mục upload
//                FileCopyUtils.copy(file.getBytes(), new File(upload + fileName));
//                product.setImage(fileName);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }else {
//            product.setImage(iProductService.findById(product.getId()).get().getImage());
//        }
        iProductService.save(product);
        return modelAndView;

    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id){
        iProductService.remove(id);
        return "redirect:/products";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id,
                            @ModelAttribute Cart cart,
                            @RequestParam("action") String action) {
        Optional<Product> productOptional = iProductService.findById(id);

        if(!productOptional.isPresent()) {
            return "/error_404";
        }
        if (action.equals("show")) {
            cart.addProduct(productOptional.get());
            return "redirect:/shopping-cart";
        }
        cart.addProduct(productOptional.get());
        return "redirect:/products";
    }
    @PostMapping("search")
    public ModelAndView findName(@RequestParam String name){
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", iProductService.findProductByNameContaining(name));
        return modelAndView;
    }

    @GetMapping("/sub/{id}")
    public String subFromCart(@PathVariable Long id,
                              @ModelAttribute Cart cart,
                              @RequestParam("action") String action) {
        Optional<Product> productOptional = iProductService.findById(id);
        if(!productOptional.isPresent()) {
            return "/error_404";
        }
        if (action.equals("show")) {
            cart.subProduct(productOptional.get());
            return "redirect:/shopping-cart";
        }
        cart.subProduct(productOptional.get());
        return "redirect:/products";
    }
}
