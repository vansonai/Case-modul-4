package com.example.casemodul4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Min(0)
    private Double price;

    private String description;

    @Min(0)
    private Long quantity;

    private String image;

    @Transient
    private MultipartFile file;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
