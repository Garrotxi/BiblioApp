package com.biblioapp.core.controller;


import com.biblioapp.core.dto.Missatge;
import com.biblioapp.core.dto.PrestecDTO;
import com.biblioapp.core.entity.Prestec;
import com.biblioapp.core.service.PrestecService;
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
 * la entitat prestec
 */

@RestController
@RequestMapping("/prestec")
@CrossOrigin(origins = "*")
public class PrestecController {
    @Autowired
    PrestecService prestecService;

    @GetMapping("/llistaPrestecs")
    public ResponseEntity<List<Prestec>> llistaPrestec() {
        List<Prestec> prestecs = prestecService.llistaPrestecs();
        return new ResponseEntity<List<Prestec>>(prestecs, HttpStatus.OK);
    }

    @GetMapping("/detallPrestecId/{idPrestec}")
    public ResponseEntity<Prestec> prestecById(@PathVariable("idPrestec") int idPrestec) {
        if (!prestecService.existsByIdPrestec(idPrestec))
            return new ResponseEntity(new Missatge("No existeix el prestec"), HttpStatus.NOT_FOUND);
        Prestec prestec = prestecService.getPrestec(idPrestec).get();
        return new ResponseEntity(prestec, HttpStatus.OK);
    }

/*
    //S'ha de cambiar a llista similar a FindAllById
    @GetMapping("detallPrestecIdUsuari/{idUsuari}")
    public ResponseEntity<List<Prestec>> prestecByIdUsuari(@PathVariable("idUsuari") int idUsuari) {
        if (!prestecService.existsByIdLlibre(idUsuari))
            return new ResponseEntity(new Missatge("No existeix el prestec per Id usuari"), HttpStatus.NOT_FOUND);
        Prestec prestec = prestecService.getByIdUsuari(idUsuari).get();
        return new ResponseEntity(prestec, HttpStatus.OK);
    }

    @GetMapping("detallPrestecIdLlibre/{idLlibre}")
    public ResponseEntity<Prestec> prestecByIdLlibre(@PathVariable("idLlibre") int idLlibre) {
        if (!prestecService.existsByIdLlibre(idLlibre))
            return new ResponseEntity(new Missatge("No existeix el prestec per Id llibre"), HttpStatus.NOT_FOUND);
        Prestec prestec = prestecService.getByIdLlibre(idLlibre).get();
        return new ResponseEntity(prestec, HttpStatus.OK);
    }
*/

    @PostMapping("/crearPrestec")
    public ResponseEntity<?> crearPrestec(@RequestBody PrestecDTO prestecDTO) {
        if(prestecDTO.getIdUsuari() == 0 || prestecDTO.getIdLlibre() == 0)
            return new ResponseEntity(new Missatge("El id d'usuari i id de llibre són oblicatoris"), HttpStatus.BAD_REQUEST);

        Prestec prestec = new Prestec(prestecDTO.getIdUsuari(), prestecDTO.getIdLlibre(),
                prestecDTO.getDataPrestec(), prestecDTO.getDataDevolucioPrevista(),
                prestecDTO.getDataDevolucio());
        prestecService.savePrestec(prestec);

        return new ResponseEntity(new Missatge("Prestec enregistrat"), HttpStatus.OK);


    }


    @PutMapping("/actualizarPrestec/{idPrestec}")
    public ResponseEntity<?> actualitzarPrestec(@PathVariable("idPrestec") int idPrestec,
                                                @RequestBody PrestecDTO prestecDTO) {
        if (!prestecService.existsByIdPrestec(idPrestec))
            return new ResponseEntity(new Missatge("No existeix el prestec"), HttpStatus.NOT_FOUND);

        Prestec prestec = prestecService.getPrestec(idPrestec).get();

        String dataDevolucioPrevistaActual = prestec.getDataDevolucioPrevista();
        String dataDevolucioActual = prestec.getDataDevolucio();

        if (!prestecDTO.getDataDevolucioPrevista().isEmpty()){
            prestec.setDataDevolucioPrevista(prestecDTO.getDataDevolucioPrevista());
        } else {
            prestec.setDataDevolucioPrevista(dataDevolucioPrevistaActual);
        }

        if (!prestecDTO.getDataDevolucio().isEmpty()) {
            prestec.setDataDevolucio(prestecDTO.getDataDevolucio());
        } else {
            prestec.setDataDevolucio(dataDevolucioActual);
        }

        prestec.setIdLlibre(prestecDTO.getIdLlibre());
        prestec.setIdUsuari(prestecDTO.getIdUsuari());
        prestec.setDataPrestec(prestecDTO.getDataPrestec());

        prestecService.savePrestec(prestec);

        return new ResponseEntity<>(prestec, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminarPrestec/{idPrestec}")
    public ResponseEntity<?> eliminarPrestec(@PathVariable("idPrestec") int idPrestec) {
        if(!prestecService.existsByIdPrestec(idPrestec))
            return new ResponseEntity<>(new Missatge("No existeix el prestec"), HttpStatus.NOT_FOUND);
        prestecService.deletePrestec(idPrestec);
        return new ResponseEntity<>(new Missatge("Prestec Eliminat"), HttpStatus.OK);
    }

}
