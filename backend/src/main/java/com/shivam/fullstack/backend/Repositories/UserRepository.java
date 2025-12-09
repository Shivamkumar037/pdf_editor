package com.shivam.fullstack.backend.Repositories;
import com.shivam.fullstack.backend.Entity.Product;
import com.shivam.fullstack.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import com.shivam.fullstack.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByMobile(String mobile);
}

