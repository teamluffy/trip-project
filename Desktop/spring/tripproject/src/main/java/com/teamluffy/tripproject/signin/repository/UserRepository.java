package com.teamluffy.tripproject.signin.repository;

import com.teamluffy.tripproject.signin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

//     유저 이메일로 회원 정보 조회
    Optional<UserEntity> findByUserEmail(String userEmail);
}
