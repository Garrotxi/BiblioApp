package com.biblioapp.core.repository;

import com.biblioapp.core.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author: Lluis Antoni Roigé Higueras
 * Conte les interficies que s'extenen de JpaRepository
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Optional<Categoria> findByNomCategoria(String nomCategoria);

    boolean existsByNomCategoria(String nomCategoria);
}
