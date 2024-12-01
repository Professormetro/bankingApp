package com.chernov.customer.service.Repository;

import com.chernov.customer.service.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
}
