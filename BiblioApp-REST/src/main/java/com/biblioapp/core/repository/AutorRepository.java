package com.biblioapp.core.repository;

import com.biblioapp.core.entity.Autor;
import com.biblioapp.core.entity.Llibre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author: Lluis Antoni Roigé Higueras
 * Conte les interficies que s'extenen de JpaRepository
 */
@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {
    Optional<Autor> findByNom(String nom);

    boolean existsByNom(String nom);
}
