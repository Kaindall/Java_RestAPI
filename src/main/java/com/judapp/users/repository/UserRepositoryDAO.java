package com.judapp.users.repository;


import com.judapp.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepositoryDAO extends JpaRepository<User, Integer> {
    Optional<User> findByUserId(int id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUserId(int id);
}
