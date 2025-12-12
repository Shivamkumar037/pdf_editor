package com.shivam.fullstack.backend.Repositories;

import com.shivam.fullstack.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a User entity by their mobile number.
     * Using Optional<User> ensures robust handling of cases where the user might not be found,
     * preventing potential NullPointerExceptions.
     * * @param mobile The user's mobile number.
     * @return An Optional containing the User if found, or empty otherwise.
     */
    Optional<User> findByMobile(String mobile);

    /**
     * Finds a User entity by their Gmail address.
     * This is highly recommended since Gmail was recently added to the User entity.
     * * @param gmail The user's Gmail address.
     * @return An Optional containing the User if found, or empty otherwise.
     */
    Optional<User> findByGmail(String gmail);
}
