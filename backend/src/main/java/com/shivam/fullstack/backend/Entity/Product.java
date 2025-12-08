package com.shivam.fullstack.backend.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productname;
    private String description;
    private String productlink;
    private String imglink;
    private Double price;

    // getters and setters
}
