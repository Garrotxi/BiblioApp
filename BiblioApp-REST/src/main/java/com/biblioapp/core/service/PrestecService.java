package com.biblioapp.core.service;


import com.biblioapp.core.entity.Prestec;
import com.biblioapp.core.repository.PrestecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Lluis Antoni Roigé Higueras
 * Implementacio dels metodes de PrestecService
 */
@Service
@Transactional
public class PrestecService {
    @Autowired
    PrestecRepository prestecRepository;

    public List<Prestec> llistaPrestecs() {
        return prestecRepository.findAll();
    }

    public Optional<Prestec> getPrestec(int idPrestec) {
        return prestecRepository.findById(idPrestec);
    }

    public Optional<Prestec> getByIdUsuari(int idUsuari) {
        return prestecRepository.findByIdUsuari(idUsuari);
    }

    public Optional<Prestec> getByIdLlibre(int idLlibre) {
        return prestecRepository.findByIdUsuari(idLlibre);
    }

    public void savePrestec(Prestec prestec) {
        prestecRepository.save(prestec);
    }

    public void deletePrestec(int idPrestec) {
        prestecRepository.deleteById(idPrestec);
    }

    public boolean existsByIdPrestec(int idPrestec) {
        return prestecRepository.existsById(idPrestec);
    }

    public boolean existsByIdUsuari(int idUsuari) {
        return prestecRepository.existsByIdUsuari(idUsuari);
    }

    public boolean existsByIdLlibre(int idLlibre) {
        return prestecRepository.existsByIdLlibre(idLlibre);
    }

}
