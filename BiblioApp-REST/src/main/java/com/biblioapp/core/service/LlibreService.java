package com.biblioapp.core.service;

import com.biblioapp.core.entity.Llibre;
import com.biblioapp.core.repository.LlibreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
/**
 * @Author: Lluis Antoni Roigé Higueras
 */

@Service
@Transactional
public class LlibreService {
    @Autowired
    LlibreRepository llibreRepository;

    public List<Llibre> llistaLlibres() {
        return llibreRepository.findAll();
    }

    public Optional<Llibre> getLlibre(int idLlibre){
        return llibreRepository.findById(idLlibre);
    }

    public Optional<Llibre> getByTitulLlibre(String titulLlibre) {
        return llibreRepository.findByTitulLlibre(titulLlibre);
    }

    public void saveLlibre(Llibre llibre){
        llibreRepository.save(llibre);
    }

    public void deleteLlibre(int idLlibre){
        llibreRepository.deleteById(idLlibre);
    }

    public boolean existsByIdLlibre(int idLlibre){
        return llibreRepository.existsById(idLlibre);
    }

    public boolean existsByTitulLlibre(String titulLlibre){
        return llibreRepository.existsByTitulLlibre(titulLlibre);
    }

}
