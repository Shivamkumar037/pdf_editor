package com.shivam.fullstack.backend.Repositories;

import com.shivam.fullstack.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // यह वही मेथड है जो UserController में इस्तेमाल हो रहा है
    User findByMobile(String mobile);
}
