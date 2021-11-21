package com.biblioapp.core.security.repository;

import com.biblioapp.core.security.entity.Rol;
import com.biblioapp.core.security.enums.RolNom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * @Author: Lluis Antoni Roigé Higueras
 */

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNom(RolNom rolNombre);
}
