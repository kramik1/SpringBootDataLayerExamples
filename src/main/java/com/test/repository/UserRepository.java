package com.test.repository;

import com.test.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository <UserEntity, Long> {

    UserEntity findAllByUsername(String username);

    //List<UserEntity> findAllByCreatedOnBeforeAndLastNameContains(LocalDateTime dateTime, String lastName);

    List<UserEntity> findAllByLastNameContains(String lastName);
}
