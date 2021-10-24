package com.biblioApp.core.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Prestec")
public class BooksAuthors {

    @Column(name="ID_LLIBRE")
    int idBook;

    @Column(name="ID_AUTOR")
    int idAuthor;

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }
}
