package com.example.casemodul4.controller;

import com.example.casemodul4.model.Product;
import com.example.casemodul4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("products")
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @GetMapping("/product")
    public ResponseEntity<Iterable<Product>> findAllProducts(){
        List<Product> productList = (List<Product>) iProductService.findAll();
        if(productList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Optional<Product>> findProductById(@PathVariable Long id){
        Optional<Product> product = iProductService.findById(id);
        if(product.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @PostMapping("/product/create")
    public ResponseEntity<Product> create(@RequestBody Product product){
        return new ResponseEntity<>(iProductService.save(product), HttpStatus.CREATED);
    }
    @PutMapping("/product/edit/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product){
        product.setId(id);
        iProductService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id){
        iProductService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
