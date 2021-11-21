package com.biblioapp.core.security.repository;

import com.biblioapp.core.security.entity.Usuari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * @Author: Lluis Antoni Roigé Higueras
 */

@Repository
public interface UsuariRepository extends JpaRepository<Usuari, Integer> {
    Optional<Usuari> findByNomUsuari(String nomUsuari);
    boolean existsByNomUsuari (String nomUsuari);
    boolean existsByEmail (String email);
}
