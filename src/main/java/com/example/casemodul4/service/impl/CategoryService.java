package com.example.casemodul4.service.impl;

import com.example.casemodul4.model.Category;
import com.example.casemodul4.repository.ICategoryRepository;
import com.example.casemodul4.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Override
    public List<Category> findAll() {
        return iCategoryRepository.findAll();
    }
    @Override
    public Page<Category> findAll(Pageable pageable){
        return iCategoryRepository.findAll(pageable);
    }

    @Override
    public Category findById(Long id) {
        return iCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Category category) {
        iCategoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        iCategoryRepository.deleteById(id);
    }
}
