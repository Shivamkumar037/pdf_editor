package com.shivam.fullstack.backend.Entity;

import jakarta.persistence.*;

/**
 * Entity class representing a User in the database.
 * Updated to include Gmail, Balance, and Tasks Completed fields for GMR Service,
 * with appropriate default initializations.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // These fields will be provided by the user during registration
    private String username;
    private String mobile;

    // Defaulting to null (explicitly for clarity, though Java default)
    private String gmail = null;

    // Defaulting balance to 0.0
    private Double balance = 0.0;

    // Defaulting tasks completed count to 0
    private int tasksCompleted = 0;

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getTasksCompleted() {
        return tasksCompleted;
    }

    public void setTasksCompleted(int tasksCompleted) {
        this.tasksCompleted = tasksCompleted;
    }
}
