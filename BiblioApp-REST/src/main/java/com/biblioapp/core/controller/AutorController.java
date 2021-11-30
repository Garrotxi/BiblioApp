package com.biblioapp.core.controller;

import com.biblioapp.core.dto.AutorDTO;
import com.biblioapp.core.dto.Missatge;
import com.biblioapp.core.entity.Autor;
import com.biblioapp.core.service.AutorService;
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
 * la entitat autor
 */

@RestController
@RequestMapping("/autor")
@CrossOrigin(origins = "*")
public class AutorController {

    @Autowired
    AutorService autorService;

    @GetMapping("/llistaAutors")
    public ResponseEntity<List<Autor>> llistaAutors(){
        List<Autor> autors = autorService.llistaAutors();
        return new ResponseEntity<List<Autor>>(autors, HttpStatus.OK);
    }

    @GetMapping("/detallAutor/{idAutor}")
    public ResponseEntity<Autor> autorById(@PathVariable("idAutor") int idAutor){
        if (!autorService.existByIdAutor(idAutor))
            return new ResponseEntity(new Missatge("No existeix l'Autor"), HttpStatus.NOT_FOUND);

        Autor autor = autorService.getAutor(idAutor).get();
        return new ResponseEntity(autor, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crearAutor")
    public ResponseEntity<?> crearAutor(@RequestBody AutorDTO autorDTO) {
        if(StringUtils.isBlank(autorDTO.getNom()))
            return new ResponseEntity(new Missatge("El nom del Autor es obligatori"), HttpStatus.BAD_REQUEST);

        if(autorService.existByNom(autorDTO.getNom()))
            return new ResponseEntity(new Missatge("Ja existeix un autor amb aquest nom"), HttpStatus.BAD_REQUEST);

        Autor autor = new Autor(autorDTO.getNom());
        autorService.saveAutor(autor);
        return new ResponseEntity(new Missatge("Autor creat"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizarAutor/{idAutor}")
    public ResponseEntity<?> actualitzarAutor(@PathVariable("idAutor") int idAutor,
                                              @RequestBody AutorDTO autorDTO) {
        if(!autorService.existByIdAutor(idAutor))
            return new ResponseEntity(new Missatge("No existeix l'Autor"), HttpStatus.NOT_FOUND);
        if(autorService.existByNom(autorDTO.getNom())
                && autorService.getByNom(autorDTO.getNom()).get().getIdAutor() != idAutor)
            return new ResponseEntity(new Missatge("El nom del Autor ja existeix"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(autorDTO.getNom()))
            return new ResponseEntity(new Missatge("El nom del Autor es obligatori"), HttpStatus.BAD_REQUEST);

        Autor autor = autorService.getAutor(idAutor).get();
        autor.setNom(autorDTO.getNom());
        autorService.saveAutor(autor);
        return new ResponseEntity(new Missatge("Autor actualitzat"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminarAutor/{idAutor}")
    public ResponseEntity<?> eliminarAutor(@PathVariable("idAutor") int idAutor) {
        if(!autorService.existByIdAutor(idAutor))
            return new ResponseEntity<>(new Missatge("No existeix l'Autor"), HttpStatus.NOT_FOUND);
        autorService.deleteAutor(idAutor);
        return new ResponseEntity<>(new Missatge("Autor eliminat"), HttpStatus.OK);
    }

}
