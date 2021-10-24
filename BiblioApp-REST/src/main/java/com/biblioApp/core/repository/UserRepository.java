package com.biblioApp.core.repository;

import java.util.Optional;

import com.biblioApp.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

    Void save(Optional<User> userToUpdate);

}
