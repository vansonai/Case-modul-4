package com.example.casemodul4.service.impl;

import com.example.casemodul4.model.Category;
import com.example.casemodul4.repository.ICategoryRepository;
import com.example.casemodul4.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository iCategoryRepository;
    @Override
    public Iterable<Category> findAll(){
        return iCategoryRepository.findAll();
    }
    @Override
    public Optional<Category> findById(Long id){
        return iCategoryRepository.findById(id);
    }
    @Override
    public void save(Category category){
        iCategoryRepository.save(category);
    }
    @Override
    public void remove(Long id){
        iCategoryRepository.deleteById(id);
    }
}
