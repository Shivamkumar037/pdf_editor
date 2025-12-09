package com.shivam.fullstack.backend.Controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.shivam.fullstack.backend.Entity.Product;
import com.shivam.fullstack.backend.Repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productdata")
public class ProductController {

    private final ProductRepository repo;
    private final Cloudinary cloudinary;

    public ProductController(ProductRepository repo, Cloudinary cloudinary) {
        this.repo = repo;
        this.cloudinary = cloudinary;
    }


    // Create Product with Image Upload
    @PostMapping(consumes = {"multipart/form-data"})
    public Product createProduct(@RequestPart("product") Product product,
                                 @RequestPart("image") MultipartFile file) throws IOException {

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("folder", "products"));

        product.setImglink(uploadResult.get("secure_url").toString());
        product.setPublicId(uploadResult.get("public_id").toString());

        return repo.save(product);
    }

    // Get All Products
    @GetMapping
    public List<Product> getProducts() {
        return repo.findAll();
    }

    // Delete Product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws IOException {
        Product p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cloudinary.uploader().destroy(p.getPublicId(), ObjectUtils.emptyMap());

        repo.delete(p);
        return ResponseEntity.ok("Product & Image Deleted Successfully");
    }

    // Update only price
    @PutMapping("/{id}/price")
    public ResponseEntity<Product> updatePrice(@PathVariable Long id, @RequestParam Double price) {
        Product p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        p.setPrice(price);
        return ResponseEntity.ok(repo.save(p));
    }
}
