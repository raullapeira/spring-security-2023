package com.example.securingweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.securingweb.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query
    public User findByEmail(String email);
}
