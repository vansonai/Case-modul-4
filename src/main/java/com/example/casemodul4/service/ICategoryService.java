package com.example.casemodul4.service;

import com.example.casemodul4.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService{
    Iterable<Category> findAll();
    Page<Category> findAll(Pageable pageable);
    Category findById(Long id);
    void save(Category category);
    void delete(Long id);
}
