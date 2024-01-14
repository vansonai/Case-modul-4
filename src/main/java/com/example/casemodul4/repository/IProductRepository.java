package com.example.casemodul4.repository;

import com.example.casemodul4.model.Category;
import com.example.casemodul4.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Iterable<Product> findAllByCategory(Category category);
}
