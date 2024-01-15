package com.example.casemodul4.repository;

import com.example.casemodul4.model.Category;
import com.example.casemodul4.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Iterable<Product> findAllByCategory(Category category);
    @Query("select p from Product p where p.name like %:name%")
    List<Product> findProductByNameContaining(String name);
}
