package com.biblioapp.core.entity;

import javax.persistence.*;

/**
 * @Author: Lluis Antoni Roigé Higueras
 */

@Entity
@Table(name = "llibres", indexes = {@Index(name = "idx_llibre_idllibre", columnList = "idLlibre")})
public class Llibre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLlibre;
    @Column(name="titul")
    private String titulLlibre;
    @Column(name="data_publicacio")
    private String dataPublicacio;
    @Column(name="copies_disponibles")
    private int copiesDisponibles;

    public Llibre() {
    }

    public Llibre(String titulLlibre, String dataPublicacio, int copiesDisponibles){
        this.titulLlibre = titulLlibre;
        this.dataPublicacio =dataPublicacio;
        this.copiesDisponibles = copiesDisponibles;
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
}
