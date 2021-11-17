package com.biblioapp.core.security.dto;

import javax.validation.constraints.NotBlank;

public class LoginUsuari {
    @NotBlank
    private String nomUsuari;
    @NotBlank
    private String contrasenya;

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
}
