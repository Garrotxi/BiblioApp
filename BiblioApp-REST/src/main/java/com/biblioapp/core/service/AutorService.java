package com.biblioapp.core.service;

import com.biblioapp.core.entity.Autor;
import com.biblioapp.core.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Lluis Antoni Roigé Higueras
 * Implementacio dels metodes de AutorService
 */

@Service
@Transactional
public class AutorService {
    @Autowired
    AutorRepository autorRepository;

    public List<Autor> llistaAutors() {
        return autorRepository.findAll();
    }

    public Optional<Autor> getAutor(int idAutor) {
        return autorRepository.findById(idAutor);
    }

    public Optional<Autor> getByNom(String nom) {
        return autorRepository.findByNom(nom);
    }

    public void saveAutor(Autor autor) {
        autorRepository.save(autor);
    }

    public void deleteAutor(int idAutor) {
        autorRepository.deleteById(idAutor);
    }

    public boolean existByIdAutor(int idAutor) {
        return autorRepository.existsById(idAutor);
    }

    public boolean existByNom(String nom) {
        return autorRepository.existsByNom(nom);
    }
}
