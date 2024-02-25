package com.example.alten.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private String description;

    private String image;

    private Integer price;

    private String category;

    private Integer quantity;

    private String inventoryStatus;

    private Integer rating;
}
