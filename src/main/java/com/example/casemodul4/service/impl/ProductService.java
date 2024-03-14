package com.example.casemodul4.service.impl;

import com.example.casemodul4.model.Category;
import com.example.casemodul4.model.Product;
import com.example.casemodul4.repository.IProductRepository;
import com.example.casemodul4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public Iterable<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        iProductRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        iProductRepository.deleteById(id);
    }

    @Override
    public Iterable<Product> findAllByCategory(Category category){
        return iProductRepository.findAllByCategory(category);
    }
    @Override
    public List<Product> findProductByNameContaining(String name){
        return iProductRepository.findProductByNameContaining(name);
    }
}
