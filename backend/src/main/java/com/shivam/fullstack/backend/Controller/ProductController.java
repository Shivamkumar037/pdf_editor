package com.shivam.fullstack.backend.Controller;

import com.shivam.fullstack.backend.Entity.User;
import com.shivam.fullstack.backend.Repositories.UserRepository;
import org.springframework.web.bind.annotation.*; // Isme CrossOrigin bhi aa jata hai
import java.util.List;

@RestController
@RequestMapping("/userdata")
@CrossOrigin(origins = "*") // <--- YE LINE ADD KI HAI (CORS Fix)
public class UserController {
    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return repo.save(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return repo.findAll();
    }
}