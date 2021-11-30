package com.biblioapp.core.dto;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Lluis Antoni Roigé Higueras
 * Clase que es limita a ser un objecte de transferencia
 * entre client i el servidor per a l'entitat Autor.
 * DTO acronim de Data Transfer Object.
 */
public class AutorDTO {
    @NotBlank
    private String nom;

    public AutorDTO() {

    }

    public AutorDTO(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
