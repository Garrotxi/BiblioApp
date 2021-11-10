package com.biblioApp.example.security.services;

import com.biblioApp.example.entity.Book;
import com.biblioApp.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/*
 * @Author: Lluis Antoni Roigé Higueras
 * BookServiceImpl hereda el llistat de metodes que
 * s'han definit en BookService.
 */

public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book;
    }

    @Override
    public Book saveBook(Book bookNew) {
        if (bookNew != null) {
            return bookRepository.save(bookNew);
        }
        return new Book();
    }

    @Override
    public String deleteBook(Long id) {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
            return "Book succesfully deleted";
        }
        return "Error! Book does not exist!";
    }

    @Override
    public String updateBook(Book bookUpdated) {
        Long num = bookUpdated.getId();
        if (bookRepository.findById(num).isPresent()) {
            Book bookToUpdate = new Book();
            bookToUpdate.setTitle(bookUpdated.getTitle());
            bookToUpdate.setCategoryId(bookUpdated.getCategoryId());
            bookToUpdate.setPublishDate(bookUpdated.getPublishDate());
            bookToUpdate.setAvailableCopies(bookUpdated.getAvailableCopies());

            bookRepository.save(bookToUpdate);

            return "Book modified!";

        }

        return null;
    }
}
