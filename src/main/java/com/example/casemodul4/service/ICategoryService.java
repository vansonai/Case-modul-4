package com.example.casemodul4.service;

import com.example.casemodul4.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService{
    List<Category> findAll();
    Page<Category> findAll(Pageable pageable);
    Category findById(Long id);
    void save(Category category);
    void deleteById(Long id);
}
