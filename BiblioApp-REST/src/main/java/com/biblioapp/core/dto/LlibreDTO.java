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
    private String isbn;
    private String descripcio;

    public LlibreDTO() {
    }

    public LlibreDTO(String titulLlibre, String dataPublicacio, int copiesDisponibles, String isbn, String descripcio){
        this.titulLlibre = titulLlibre;
        this.dataPublicacio =dataPublicacio;
        this.copiesDisponibles = copiesDisponibles;
        this.isbn = isbn;
        this.descripcio = descripcio;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }
}
