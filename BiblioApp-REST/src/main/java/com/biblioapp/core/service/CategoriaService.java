package com.biblioapp.core.service;

import com.biblioapp.core.entity.Categoria;
import com.biblioapp.core.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Lluis Antoni Roigé Higueras
 * Implementacio dels metodes de CategoriaService
 */
@Service
@Transactional
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> llistaCategories() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> getCategoria(int idCategoria) {
        return categoriaRepository.findById(idCategoria);
    }

    public Optional<Categoria> getByNomCategoria(String nomCategoria) {
        return categoriaRepository.findByNomCategoria(nomCategoria);
    }

    public void saveCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    public void deleteCategoria(int idCategoria) {
        categoriaRepository.deleteById(idCategoria);
    }

    public boolean existsByIdCategoria(int idCategoria) {
        return categoriaRepository.existsById(idCategoria);
    }

    public boolean existsByNomCategoria(String nomCategoria) {
        return categoriaRepository.existsByNomCategoria(nomCategoria);
    }
}
