package com.sportyshoes.repository;

import com.sportyshoes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Search method for finding users by username or email containing a keyword
    List<User> findByUsernameContainingOrEmailContainingAllIgnoreCase(String username, String email);
    List<User> findByUsernameContainingIgnoreCase(String username);

}

