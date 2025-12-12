package com.shivam.fullstack.backend.Controller;

import com.shivam.fullstack.backend.Entity.User;
import com.shivam.fullstack.backend.Repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gmr") // Iska URL alag rakha hai taaki conflict na ho
@CrossOrigin(origins = "*")
public class GmrController {

    private final UserRepository repo;

    public GmrController(UserRepository repo) {
        this.repo = repo;
    }

    // 1. User Register/Login Logic (Balance Preserve karne wala)
    @PostMapping("/register")
    public ResponseEntity<User> registerGmrUser(@RequestBody User inputUser) {
        // Mobile number se check karein ki user pehle se hai ya nahi
        User existingUser = repo.findByMobile(inputUser.getMobile());

        if (existingUser != null) {
            // User Purana Hai:
            // 1. Username update karein (agar naya aaya hai)
            if(inputUser.getUsername() != null) {
                existingUser.setUsername(inputUser.getUsername());
            }
            // 2. Gmail update karein (agar naya aaya hai)
            if(inputUser.getGmail() != null) {
                existingUser.setGmail(inputUser.getGmail());
            }

            // NOTE: Balance ko touch nahi kiya, purana balance hi rahega.

            return ResponseEntity.ok(repo.save(existingUser));
        } else {
            // User Naya Hai:
            // Default balance 0.0 entity me set hai, to bas save kar dein
            // Agar frontend se gmail nahi aaya to woh null rahega (jaisa aapne kaha)
            return ResponseEntity.ok(repo.save(inputUser));
        }
    }

    // 2. Balance Add karne ki API (Frontend se amount aayega aur jud jayega)
    @PostMapping("/add-balance")
    public ResponseEntity<?> addBalance(@RequestParam String mobile, @RequestParam Double amount) {
        User user = repo.findByMobile(mobile);

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found with mobile: " + mobile);
        }

        // Purane balance me naya amount jod dein
        Double currentBalance = user.getBalance() != null ? user.getBalance() : 0.0;
        user.setBalance(currentBalance + amount);

        return ResponseEntity.ok(repo.save(user));
    }
}