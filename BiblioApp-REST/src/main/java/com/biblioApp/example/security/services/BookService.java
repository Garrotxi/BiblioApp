package com.biblioApp.example.security.services;


import com.biblioApp.example.entity.Book;

import java.util.List;
import java.util.Optional;

/*
 * @Author: Lluis Antoni Roigé Higueras
 * Interficie en la que afegirem el llistat de metodes que
 * posteriorment s'implementaran en BookServiceImpl.
 */


public interface BookService {
    public List<Book> findAllBooks();

    public Optional<Book> findBookById(Long id);

    public Book saveBook(Book bookNew);

    public String deleteBook(Long id);

    public String updateBook(Book bookUpdated);
}
