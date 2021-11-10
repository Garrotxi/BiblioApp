package com.biblioApp.example.entity;


/*
 * @Author: Lluis Antoni Roigé Higueras
 *
 * S'afegeix la entitat que mapejara les dades de la taula de BBDD que
 * li especifiquem per a posteriorment transformar-los en objectes
 */



import javax.persistence.*;

@Entity
@Table(name="llibre")
public class Book {

    public Book() {

    }

    public Book(String title, int categoryId, String publishDate, int availableCopies) {
        this.title = title;
        this.categoryId = categoryId;
        this.publishDate = publishDate;
        this.availableCopies = availableCopies;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="titul")
    private String title;

    @Column(name="id_categoria")
    private int categoryId;

    @Column(name="data_publicacio")
    private String publishDate;

    @Column(name="copies_disponibles")
    private int availableCopies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
}
