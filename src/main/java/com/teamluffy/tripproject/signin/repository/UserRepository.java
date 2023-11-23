package com.teamluffy.tripproject.signin.repository;

import com.teamluffy.tripproject.signin.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmailAndPassword(String email, String password);

    Optional<Users> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Users> findByUsername(String username);

}
