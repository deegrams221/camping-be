package com.dianagrams.repositories;

import java.util.List;

import com.dianagrams.models.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByUsernameContainingIgnoreCase(String name);
}
