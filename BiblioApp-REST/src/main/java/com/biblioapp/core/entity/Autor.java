package com.biblioapp.core.entity;

import javax.persistence.*;

/**
 * @Author: Lluis Antoni Roigé Higueras
 */

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAutor;
    @Column(name="nom")
    private String nom;

    public Autor() {
    }

    public Autor(String nom){
        this.nom = nom;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
