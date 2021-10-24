package com.biblioApp.core.entities;


import javax.persistence.*;

@Entity
@Table(name = "Llibres")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    int id;

    @Column(name="TITUL")
    String name;

    @Column(name="ID_CATEGORIA")
    int idCategory;

    @Column(name="DATA_PUBLICACIO")
    String date;

    @Column(name="COPIES_DISPONIBLES")
    int availableCopies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
}
