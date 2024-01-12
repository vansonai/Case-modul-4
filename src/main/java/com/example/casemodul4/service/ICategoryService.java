package com.example.casemodul4.service;

import com.example.casemodul4.model.Category;

public interface ICategoryService{
    Iterable<Category> findAll();
    Category findById(Long id);
    void save(Category category);
    void delete(Long id);
}
