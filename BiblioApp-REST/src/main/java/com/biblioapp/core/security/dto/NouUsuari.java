package com.biblioapp.core.security.dto;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lluis Antoni Roigé Higueras
 */

public class NouUsuari {
    @NotNull
    private String nom;

    @NotNull
    private String nomUsuari;

    @NotNull
    private String contrasenya;

    @NotNull
    private String email;
    private List<String> rols = new ArrayList();

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

    public List<String> getRols() {
        return rols;
    }

    public void setRols(List<String> rols) {
        this.rols = rols;
    }
}
