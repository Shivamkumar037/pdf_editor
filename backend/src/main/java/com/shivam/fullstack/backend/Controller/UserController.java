package com.shivam.fullstack.backend.Controller;

import com.shivam.fullstack.backend.Entity.User;
import com.shivam.fullstack.backend.Repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userdata")
@CrossOrigin(origins = "*") // यह लाइन जरूरी है (Frontend connection के लिए)
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<User> createOrUpdateUser(@RequestBody User user) {
        // Check if mobile already exists
        User existingUser = repo.findByMobile(user.getMobile());

        if (existingUser != null) {
            // Update existing record (sirf naam update karein agar user wapis aaya hai)
            existingUser.setUsername(user.getUsername());
            return ResponseEntity.ok(repo.save(existingUser));
        }

        // New user create
        return ResponseEntity.ok(repo.save(user));
    }

    @GetMapping
    public List<User> getUsers() {
        return repo.findAll();
    }
}
