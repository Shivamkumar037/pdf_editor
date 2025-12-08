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

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }
    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductlink() {
        return productlink;
    }
    public void setProductlink(String productlink) {
        this.productlink = productlink;
    }

    public String getImglink() {
        return imglink;
    }
    public void setImglink(String imglink) {
        this.imglink = imglink;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
