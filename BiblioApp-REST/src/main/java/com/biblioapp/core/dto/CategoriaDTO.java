package com.biblioapp.core.dto;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Lluis Antoni Roigé Higueras
 * Clase que es limita a ser un objecte de transferencia
 * entre client i el servidor per a l'entitat Categoria.
 * DTO acronim de Data Transfer Object.
 */

public class CategoriaDTO {
    @NotBlank
    private String nomCategoria;

    public CategoriaDTO() {

    }

    public CategoriaDTO(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }
}

