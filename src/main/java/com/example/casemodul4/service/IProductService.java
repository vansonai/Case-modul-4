package com.example.casemodul4.service;

import com.example.casemodul4.model.Category;
import com.example.casemodul4.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService{
    Iterable<Product> findAll();
    Optional<Product> findById(Long id);
    void save(Product product);
    void remove(Long id);
    Iterable<Product> findAllByCategory(Category category);
    List<Product> findProductByNameContaining(String name);
}
