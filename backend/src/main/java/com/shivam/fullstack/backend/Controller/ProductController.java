package com.shivam.fullstack.backend.Controller;

import com.shivam.fullstack.backend.Entity.Product;
import com.shivam.fullstack.backend.Repositories.ProductRepository;
import org.springframework.web.bind.annotation.*; // Isme CrossOrigin bhi aa jata hai
import java.util.List;

@RestController
@RequestMapping("/productdata")
@CrossOrigin(origins = "*") // <--- YE LINE ADD KI HAI (CORS Fix)
public class ProductController {
    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return repo.save(product);
    }

    @GetMapping
    public List<Product> getProducts() {
        return repo.findAll();
    }
}