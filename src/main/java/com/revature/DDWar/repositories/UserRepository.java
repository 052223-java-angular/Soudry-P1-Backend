package com.revature.DDWar.repositories;
// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.DDWar.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Finds a user by username.
     *
     * @param username the username to search for
     * @return an Optional containing the User object if found, or an empty Optional
     *         otherwise
     */
    // Optional<User> findByUsername(String username);

    // Optional<User> getUsername(String username);
}
