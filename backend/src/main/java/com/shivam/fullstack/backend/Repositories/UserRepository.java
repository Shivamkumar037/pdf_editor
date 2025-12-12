package com.shivam.fullstack.backend.Repositories;

import com.shivam.fullstack.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Mobile number se user dhundne ke liye method
    User findByMobile(String mobile);
}
