package com.biblioapp.core.dto;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Lluis Antoni Roigé Higueras
 * Clase que es limita a ser un objecte de transferencia
 * entre client i el servidor per a l'entitat Llibre.
 * DTO acronim de Data Transfer Object.
 */

public class LlibreDTO {
    @NotBlank
    private String titulLlibre;
    private String dataPublicacio;
    private int copiesDisponibles;

    public LlibreDTO() {
    }

    public LlibreDTO(String titulLlibre, String dataPublicacio, int copiesDisponibles){
        this.titulLlibre = titulLlibre;
        this.dataPublicacio =dataPublicacio;
        this.copiesDisponibles = copiesDisponibles;
    }

    public String getTitulLlibre() {
        return titulLlibre;
    }

    public void setTitulLlibre(String titulLlibre) {
        this.titulLlibre = titulLlibre;
    }

    public String getDataPublicacio() {
        return dataPublicacio;
    }

    public void setDataPublicacio(String dataPublicacio) {
        this.dataPublicacio = dataPublicacio;
    }

    public int getCopiesDisponibles() {
        return copiesDisponibles;
    }

    public void setCopiesDisponibles(int copiesDisponibles) {
        this.copiesDisponibles = copiesDisponibles;
    }
}
