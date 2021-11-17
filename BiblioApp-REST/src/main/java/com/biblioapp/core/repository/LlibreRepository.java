package com.biblioapp.core.repository;

import com.biblioapp.core.entity.Llibre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LlibreRepository extends JpaRepository<Llibre, Integer> {
    Optional<Llibre> findByTitulLlibre(String titulLlibre);

    boolean existsByTitulLlibre(String titulLlibre);

}
