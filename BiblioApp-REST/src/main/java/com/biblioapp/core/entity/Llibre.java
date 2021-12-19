package com.biblioapp.core.entity;

import javax.persistence.*;

/**
 * @Author: Lluis Antoni Roigé Higueras
 */

@Entity
//Anotacio Index generada per JPA Buddy
@Table(name = "llibres", indexes = {@Index(name = "idx_llibre_idllibre", columnList = "idLlibre")})
public class Llibre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLlibre;

    @Column(name="idAutor")
    private int idAutor;
    @Column(name="idCategoria")
    private int idCategoria;
    @Column(name="titul")
    private String titulLlibre;
    @Column(name="data_publicacio")
    private String dataPublicacio;
    @Column(name="copies_disponibles")
    private int copiesDisponibles;
    @Column(name="ISBN")
    private String isbn;
    @Column(name="Descripcio")
    private String descripcio;

    public Llibre() {
    }

    public Llibre(int idAutor, int idCategoria, String titulLlibre, String dataPublicacio, int copiesDisponibles, String isbn, String descripcio){
        this.idAutor = idAutor;
        this.idCategoria = idCategoria;
        this.titulLlibre = titulLlibre;
        this.dataPublicacio =dataPublicacio;
        this.copiesDisponibles = copiesDisponibles;
        this.isbn = isbn;
        this.descripcio = descripcio;
    }

    public int getIdLlibre() {
        return idLlibre;
    }

    public void setIdLlibre(int idLlibre) {
        this.idLlibre = idLlibre;
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
