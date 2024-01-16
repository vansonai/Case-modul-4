package com.example.casemodul4.service;

import com.example.casemodul4.model.Category;
import com.example.casemodul4.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService extends IGenerateService<Product>{
    Iterable<Product> findAllByCategory(Category category);
    List<Product> findProductByNameContaining(String name);
}
