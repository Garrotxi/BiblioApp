package com.biblioapp.core.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @Author: Lluis Antoni Roigé Higueras
 */
@Entity
@Table(name="categories")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;

    @NotBlank
    @Column(name="categoria")
    private String nomCategoria;

    public Categoria() {

    }

    public Categoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }
}
