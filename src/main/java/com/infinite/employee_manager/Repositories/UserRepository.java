package com.infinite.employee_manager.Repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.infinite.employee_manager.Models.User;


public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
