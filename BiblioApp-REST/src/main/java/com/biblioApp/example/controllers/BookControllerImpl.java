package com.biblioApp.example.controllers;


import com.biblioApp.example.entity.Book;
import com.biblioApp.example.security.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

/*
 * @Author: Lluis Antoni Roigé Higueras
 */
public class BookControllerImpl implements BookController {
    @Autowired
    BookService bookService;

    @RequestMapping(value="/book", method = RequestMethod.GET, produces = "application/json")
    @Override
    public List<Book> getBooks() {
        return bookService.findAllBooks();
    }

    @Override
    @RequestMapping(value="/book/{id}",method=RequestMethod.GET,produces="application/json")
    public Optional<Book> getBooksById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @Override
    @RequestMapping(value="/book/add",method=RequestMethod.GET,produces="application/json")
    public Book addBook(Book book) {
        return bookService.saveBook(book);
    }

    @Override
    @RequestMapping(value="/book/delete/{id}",method=RequestMethod.GET,produces="application/json")
    public String deleteCustomer(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @Override
    @RequestMapping(value="/book/update",method=RequestMethod.PATCH,produces="application/json")
    public String updateBook(Book updateBook) {
        return bookService.updateBook(updateBook);
    }

    @Override
    @RequestMapping(value="/book/test",method=RequestMethod.GET,produces="application/json")
    public String test() {
        return "Test done";
    }
}
