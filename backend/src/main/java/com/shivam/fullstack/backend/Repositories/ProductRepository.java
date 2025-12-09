package com.shivam.fullstack.backend.Repositories;

import com.shivam.fullstack.backend.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}

