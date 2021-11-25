package com.biblioapp.core.security.controller;

import com.biblioapp.core.dto.Missatge;
import com.biblioapp.core.security.dto.JwtDto;
import com.biblioapp.core.security.dto.LoginUsuari;
import com.biblioapp.core.security.dto.NouUsuari;
import com.biblioapp.core.security.dto.UsuariDTO;
import com.biblioapp.core.security.entity.Rol;
import com.biblioapp.core.security.entity.Usuari;
import com.biblioapp.core.security.enums.RolNom;
import com.biblioapp.core.security.jwt.JwtProvider;
import com.biblioapp.core.security.service.RolService;
import com.biblioapp.core.security.service.UsuariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lluis Antoni Roigé Higueras
 * Exposem els endpoints per a crear nous usuaris, realitzar-ne el login
 * llistar-los, actualitzar-los i eliminar-los
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuariService usuariService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    /**
     * Espera un json i el converteix a tipus clase NouUsuari
     */
    @PostMapping("/registrarUsuari")
    public ResponseEntity<?> registrarUsuari(@Valid @RequestBody NouUsuari nouUsuari,
                                          BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(new Missatge("Camps malament o email invalid"), HttpStatus.BAD_REQUEST);
        }
        if(usuariService.existsByUsuari(nouUsuari.getNomUsuari())){
            return new ResponseEntity<>(new Missatge("Aquest nom ja existeix"), HttpStatus.BAD_REQUEST);
        }
        if(usuariService.existsByEmail(nouUsuari.getEmail())){
            return new ResponseEntity<>(new Missatge("Aquest email ja existeix"), HttpStatus.BAD_REQUEST);
        }

        Usuari usuari = new Usuari(
                nouUsuari.getNomUsuari(),
                passwordEncoder.encode(nouUsuari.getContrasenya()),
                nouUsuari.getEmail(),
                nouUsuari.getNom(),
                nouUsuari.getCognoms(),
                nouUsuari.getTelefon()
        );

        List<Rol> rols = new ArrayList();
        rols.add(rolService.getByRolNom(RolNom.ROLE_USER).get());
        if(nouUsuari.getRols().contains("admin"))
            rols.add(rolService.getByRolNom(RolNom.ROLE_ADMIN).get());
        usuari.setRols(rols);

        usuariService.save(usuari);

        return new ResponseEntity<>(new Missatge("Usuari creat"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuari loginUsuari, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Missatge("Camps malament"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginUsuari.getNomUsuari(),
                                loginUsuari.getContrasenya()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/llistaUsuaris")
    public ResponseEntity<List<Usuari>> llistaUsuaris(){

        List<Usuari> usuaris = usuariService.llistaUsuaris();
        return new ResponseEntity<>(usuaris, HttpStatus.OK);
    }

    @PutMapping("/actualitzarUsuari/{idUsuari}")
    public ResponseEntity<?> actualitzarUsuaris(@PathVariable("idUsuari") int idUsuari,
                                                @RequestBody UsuariDTO usuariDTO){
        if (!usuariService.existsByIdUsuari(idUsuari))
            return new ResponseEntity(new Missatge("No existeix el usuari"), HttpStatus.NOT_FOUND);

        Usuari usuari = usuariService.getUsuari(idUsuari).get();
        String nomUsuariActual = usuari.getNomUsuari();
        String nomActual = usuari.getNom();
        String cognomsActual = usuari.getCognoms();
        String emailActual = usuari.getEmail();
        String telefonActual = usuari.getTelefon();
        String contrasenyaActual = usuari.getContrasenya();

        /**
         * Comprobem quins camps venen informats, en cas de no venir informat
         * es mantindran les dades
         */
        if (!usuariDTO.getNomUsuari().isEmpty()){
            usuari.setNomUsuari(usuariDTO.getNomUsuari());
        } else {
            usuari.setNomUsuari(nomUsuariActual);
        }
        usuari.setNomUsuari(usuariDTO.getNomUsuari());
        if (!usuariDTO.getNom().isEmpty()) {
            usuari.setNom(usuariDTO.getNom());
        } else {
            usuari.setNom(nomActual);
        }
        if (!usuariDTO.getCognoms().isEmpty()) {
            usuari.setCognoms(usuariDTO.getCognoms());
        } else {
            usuari.setCognoms(cognomsActual);
        }
        if (!usuariDTO.getEmail().isEmpty()) {
            usuari.setEmail(usuariDTO.getEmail());
        } else {
            usuari.setEmail(emailActual);
        }
        if (!usuariDTO.getTelefon().isEmpty()) {
            usuari.setTelefon(usuariDTO.getTelefon());
        } else {
            usuari.setTelefon(telefonActual);
        }
        if (!usuariDTO.getContrasenya().isEmpty()) {
            usuari.setContrasenya(passwordEncoder.encode(usuariDTO.getContrasenya()));
        } else {
            usuari.setContrasenya(contrasenyaActual);
        }
        //Operacio disenyada per a mantenir els rols del usuari al actualitzar-lo
        List<Rol> rols = usuariService.getUsuari(usuari.getIdUsuari()).get().getRols();

        usuari.setRols(rols);
        usuariService.save(usuari);
        return new ResponseEntity<>(usuari, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminarUsuari/{idUsuari}")
    public ResponseEntity<?> eliminarUsuari(@PathVariable("idUsuari") int idUsuari) throws Exception {
        try {
            usuariService.delete(idUsuari);
        } catch (Exception e) {
            throw new Exception("Error al eliminar l'usuari");
        }
        return new ResponseEntity<>(new Missatge("Usuari Eliminat"), HttpStatus.OK);
    }
}
