package com.biblioApp.example.controllers;


import com.biblioApp.example.entity.Book;

import java.util.List;
import java.util.Optional;

/*
 * @Author: Lluis Antoni Roigé Higueras
 */
public interface BookController {

    public List<Book> getBooks();

    public Optional<Book> getBooksById(Long id);

    public Book addBook(Book book);

    public String deleteCustomer(Long id);

    public String updateBook(Book updateBook);

    public String test();

}
