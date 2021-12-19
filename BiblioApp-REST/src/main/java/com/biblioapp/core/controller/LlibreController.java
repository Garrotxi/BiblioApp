package com.biblioapp.core.controller;

import com.biblioapp.core.dto.LlibreDTO;
import com.biblioapp.core.dto.Missatge;
import com.biblioapp.core.entity.Llibre;
import com.biblioapp.core.service.LlibreService;
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
 * la entitat llibre
 */

@RestController
@RequestMapping("/llibre")
@CrossOrigin(origins = "*")
public class LlibreController {
    @Autowired
    LlibreService llibreService;

    @GetMapping("/llistaLlibres")
    public ResponseEntity<List<Llibre>> llistaLlibre(){
        List<Llibre> llibres = llibreService.llistaLlibres();
        return new ResponseEntity<>(llibres, HttpStatus.OK);
    }

    @GetMapping("/detallLlibre/{idLlibre}")
    public ResponseEntity<Llibre> llibreById(@PathVariable("idLlibre") int idLlibre){

        if (!llibreService.existsByIdLlibre(idLlibre))
            return new ResponseEntity(new Missatge("No existeix el llibre"), HttpStatus.NOT_FOUND);

        Llibre llibre = llibreService.getLlibre(idLlibre).get();
        return new ResponseEntity(llibre, HttpStatus.OK);
    }

    @GetMapping("/detallTitul/{titulLlibre}")
    public ResponseEntity<Llibre> llibreByTitul(@PathVariable("titulLlibre") String titulLlibre){

        if (!llibreService.existsByTitulLlibre(titulLlibre))
            return new ResponseEntity(new Missatge("No existeix el llibre"), HttpStatus.NOT_FOUND);

        Llibre llibre = llibreService.getByTitulLlibre(titulLlibre).get();
        return new ResponseEntity(llibre, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crearLlibre")
    public ResponseEntity<?> crearLlibre(@RequestBody LlibreDTO llibreDTO){

        if(StringUtils.isBlank(llibreDTO.getTitulLlibre()))
            return new ResponseEntity(new Missatge("El titul es obligatori"), HttpStatus.BAD_REQUEST);

        if(llibreService.existsByTitulLlibre(llibreDTO.getTitulLlibre()))
            return new ResponseEntity(new Missatge("Ja existeix un llibre amb aquest titul"), HttpStatus.BAD_REQUEST);

        Llibre llibre = new Llibre(llibreDTO.getIdAutor(), llibreDTO.getIdCategoria(), llibreDTO.getTitulLlibre(),
                llibreDTO.getDataPublicacio(), llibreDTO.getCopiesDisponibles(), llibreDTO.getIsbn(), llibreDTO.getDescripcio());
        llibreService.saveLlibre(llibre);
        return new ResponseEntity(new Missatge("Llibre enregistrat"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizarLlibre/{idLlibre}")
    public ResponseEntity<?> actualitzarLlibre(@PathVariable("idLlibre") int idLlibre,
                                               @RequestBody LlibreDTO llibreDTO){

        if (!llibreService.existsByIdLlibre(idLlibre))
            return new ResponseEntity(new Missatge("No existeix el llibre"), HttpStatus.NOT_FOUND);

        if (llibreService.existsByTitulLlibre(llibreDTO.getTitulLlibre())
                && llibreService.getByTitulLlibre(llibreDTO.getTitulLlibre()).get().getIdLlibre() != idLlibre)
            return new ResponseEntity(new Missatge("El titul del llibre ja existeix"), HttpStatus.NOT_FOUND);

        if(StringUtils.isBlank(llibreDTO.getTitulLlibre()))
            return new ResponseEntity(new Missatge("El titul es obligatori"), HttpStatus.BAD_REQUEST);


        Llibre llibre = llibreService.getLlibre(idLlibre).get();
        llibre.setIdAutor(llibreDTO.getIdAutor());
        llibre.setIdCategoria(llibreDTO.getIdCategoria());
        llibre.setTitulLlibre(llibreDTO.getTitulLlibre());
        llibre.setDataPublicacio(llibreDTO.getDataPublicacio());
        llibre.setCopiesDisponibles(llibreDTO.getCopiesDisponibles());
        llibre.setIsbn(llibreDTO.getIsbn());
        llibre.setDescripcio(llibreDTO.getDescripcio());
        llibreService.saveLlibre(llibre);
        return new ResponseEntity(new Missatge("Llibre actualitzat"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminarLlibre/{idLlibre}")
    public ResponseEntity<?> eliminarLlibre(@PathVariable("idLlibre") int idLlibre) {
        if(!llibreService.existsByIdLlibre(idLlibre))
            return new ResponseEntity<>(new Missatge("No existeix el llibre"), HttpStatus.NOT_FOUND);
        llibreService.deleteLlibre(idLlibre);
        return new ResponseEntity<>(new Missatge("Llibre Eliminat"), HttpStatus.OK);
    }
}
