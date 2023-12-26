package com.training.AngularSpring.repository;


import org.springframework.data.repository.CrudRepository;
import com.training.AngularSpring.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findAll();

    User findByUserId(int id);

    boolean existsByEmail (String email);

    boolean existsByUserId (int id);

    void delete(User user);

    User save(User user);
}
