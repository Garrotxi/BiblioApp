package com.biblioapp.core.repository;

import com.biblioapp.core.entity.Prestec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Author: Lluis Antoni Roigé Higueras
 * Conte les interficies que s'extenen de JpaRepository
 */
@Repository
public interface PrestecRepository extends JpaRepository<Prestec, Integer> {
    Optional<Prestec> findByIdUsuari(int idUsuari);

    boolean existsByIdUsuari(int idUsuari);

    Optional<Prestec> findByIdLlibre(int idLlibre);

    boolean existsByIdLlibre(int idLlibre);
}
