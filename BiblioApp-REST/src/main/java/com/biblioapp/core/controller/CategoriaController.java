package com.biblioapp.core.controller;

import com.biblioapp.core.dto.CategoriaDTO;
import com.biblioapp.core.dto.Missatge;
import com.biblioapp.core.entity.Categoria;
import com.biblioapp.core.service.CategoriaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Lluis Antoni Roigé Higueras
 * Exposem els endpoints per a poder realitzar operacions sobre
 * la entitat categoria
 */

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/llistaCategories")
    public ResponseEntity<List<Categoria>> llistaCategoria() {
        List<Categoria> categories = categoriaService.llistaCategories();
        return new ResponseEntity<List<Categoria>>(categories, HttpStatus.OK);
    }
    
    @GetMapping("/detallCategoria/{idCategoria}")
    public ResponseEntity<Categoria> categoriaById(@PathVariable("idCategoria") int idCategoria){

        if (!categoriaService.existsByIdCategoria(idCategoria))
            return new ResponseEntity(new Missatge("No existeix la categoria"), HttpStatus.NOT_FOUND);

        Categoria categoria = categoriaService.getCategoria(idCategoria).get();
        return new ResponseEntity(categoria, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crearCategoria")
    public ResponseEntity<?> crearCategoria(@RequestBody CategoriaDTO categoriaDTO){

        if(StringUtils.isBlank(categoriaDTO.getNomCategoria()))
            return new ResponseEntity(new Missatge("El nom de la categoria es obligatori"), HttpStatus.BAD_REQUEST);

        if(categoriaService.existsByNomCategoria(categoriaDTO.getNomCategoria()))
            return new ResponseEntity(new Missatge("Ja existeix una categoria amb aquest titul"), HttpStatus.BAD_REQUEST);

        Categoria categoria = new Categoria(categoriaDTO.getNomCategoria());
        categoriaService.saveCategoria(categoria);
        return new ResponseEntity(new Missatge("Categoria enregistrada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizarCategoria/{idCategoria}")
    public ResponseEntity<?> actualitzarCategoria(@PathVariable("idCategoria") int idCategoria,
                                                  @RequestBody CategoriaDTO categoriaDTO){

        if (!categoriaService.existsByIdCategoria(idCategoria))
            return new ResponseEntity(new Missatge("No existeix la categoria"), HttpStatus.NOT_FOUND);

        if (categoriaService.existsByNomCategoria(categoriaDTO.getNomCategoria())
                && categoriaService.getByNomCategoria(categoriaDTO.getNomCategoria()).get().getIdCategoria() != idCategoria)
            return new ResponseEntity(new Missatge("Aquesta categoria ja existeix"), HttpStatus.NOT_FOUND);

        if(StringUtils.isBlank(categoriaDTO.getNomCategoria()))
            return new ResponseEntity(new Missatge("El nom de la categoria es obligatori"), HttpStatus.BAD_REQUEST);


        Categoria categoria = categoriaService.getCategoria(idCategoria).get();
        categoria.setNomCategoria(categoriaDTO.getNomCategoria());
        categoriaService.saveCategoria(categoria);
        return new ResponseEntity(new Missatge("Categoria actualitzada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminarCategoria/{idCategoria}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable("idCategoria") int idCategoria) {
        if(!categoriaService.existsByIdCategoria(idCategoria))
            return new ResponseEntity<>(new Missatge("No existeix la Categoria"), HttpStatus.NOT_FOUND);
        categoriaService.deleteCategoria(idCategoria);
        return new ResponseEntity<>(new Missatge("Categoria eliminada"), HttpStatus.OK);
    }


}
