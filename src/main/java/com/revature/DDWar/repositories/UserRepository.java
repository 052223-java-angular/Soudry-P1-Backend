package com.revature.DDWar.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.DDWar.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Optional<User> findByUsername(String username);

    Optional<User> findByUsername(String username);
    User getReferenceById(String username);
}
