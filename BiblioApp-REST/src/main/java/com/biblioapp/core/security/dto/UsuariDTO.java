package com.biblioapp.core.security.dto;

import com.biblioapp.core.security.entity.Rol;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class UsuariDTO {
    @NotBlank
    private String nomUsuari;

    private String contrasenya;
    private String email;
    private String nom;
    private String cognoms;
    private String telefon;
    private List<Rol> rols = new ArrayList();

    public UsuariDTO() {

    }

    public UsuariDTO(String nomUsuari, String contrasenya, String email, String nom, String cognoms,
                     String telefon) {
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
        this.email = email;
        this.nom = nom;
        this.cognoms = cognoms;
        this.telefon = telefon;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public List<Rol> getRols() {
        return rols;
    }

    public void setRols(List<Rol> rols) {
        this.rols = rols;
    }
}
