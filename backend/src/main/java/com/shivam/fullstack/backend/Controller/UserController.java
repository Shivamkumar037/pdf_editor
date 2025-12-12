package com.shivam.fullstack.backend.Controller;

import com.shivam.fullstack.backend.Entity.User;
import com.shivam.fullstack.backend.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userdata")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    /**
     * Endpoint for initial user registration or login attempt validation (User Login from Frontend).
     * It ensures the user exists based on mobile or gmail, or creates a new user
     * with initial balance and task count set to zero.
     * * @param newUser A User object containing username, mobile, and gmail from the frontend login.
     * @return The created or updated User entity.
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User newUser) {

        // 1. Check for existing user by mobile
        Optional<User> existingUserOpt = repo.findByMobile(newUser.getMobile());

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();

            // Update basic profile details if user returns
            existingUser.setUsername(newUser.getUsername());
            existingUser.setGmail(newUser.getGmail());

            // Balance and tasksCompleted are NOT modified here if the user already exists
            // as they are updated via a separate status endpoint.
            return ResponseEntity.ok(repo.save(existingUser));
        }

        // 2. Check for existing user by gmail (secondary identifier)
        Optional<User> existingUserByGmailOpt = repo.findByGmail(newUser.getGmail());

        if (existingUserByGmailOpt.isPresent()) {
            User existingUser = existingUserByGmailOpt.get();

            // If user exists, update basic profile details if provided
            existingUser.setUsername(newUser.getUsername());
            existingUser.setMobile(newUser.getMobile());

            return ResponseEntity.ok(repo.save(existingUser));
        }

        // 3. New user creation: Set initial balance and task count
        newUser.setBalance(0.0);
        newUser.setTasksCompleted(0);

        return new ResponseEntity<>(repo.save(newUser), HttpStatus.CREATED);
    }

    /**
     * Endpoint to fetch all registered users. (Typically for Admin view)
     */
    @GetMapping
    public List<User> getUsers() {
        return repo.findAll();
    }

    /**
     * Dedicated endpoint to update user's financial and task completion status.
     * * @param mobile The unique mobile number of the user to update.
     * @param balance The new balance value (optional).
     * @param tasksCompleted The new total number of tasks completed (optional).
     * @return The updated User entity or 404 if not found.
     */
    @PutMapping("/update/status/{mobile}")
    public ResponseEntity<User> updateStatus(
            @PathVariable String mobile,
            @RequestParam(required = false) Double balance,
            @RequestParam(required = false) Integer tasksCompleted) {

        Optional<User> userOpt = repo.findByMobile(mobile);

        if (userOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User userToUpdate = userOpt.get();

        // Only update fields if they are provided in the request
        if (balance != null) {
            userToUpdate.setBalance(balance);
        }
        if (tasksCompleted != null) {
            userToUpdate.setTasksCompleted(tasksCompleted);
        }

        return ResponseEntity.ok(repo.save(userToUpdate));
    }
}
