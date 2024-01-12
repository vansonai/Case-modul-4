package com.example.casemodul4.service;

import com.example.casemodul4.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService{
    Page<Product> findAll(Pageable pageable);
    Product findById(Long id);
    void save(Product product);
    void delete(Long id);
}
