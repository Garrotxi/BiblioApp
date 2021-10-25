package com.biblioApp.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioApp.example.entity.Role;
import com.biblioApp.example.entity.ERole;
import com.biblioApp.example.entity.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
