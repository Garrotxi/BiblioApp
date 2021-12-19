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

    private int idAutor;
    private int idCategoria;
    private String dataPublicacio;
    private int copiesDisponibles;
    private String isbn;
    private String descripcio;

    public LlibreDTO() {
    }

    public LlibreDTO(int idAutor, int idCategoria, String titulLlibre, String dataPublicacio, int copiesDisponibles, String isbn, String descripcio){
        this.idAutor = idAutor;
        this.idCategoria = idCategoria;
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

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
