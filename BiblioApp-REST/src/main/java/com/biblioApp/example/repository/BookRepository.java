package com.biblioApp.example.repository;


import com.biblioApp.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
 * @Author: Lluis Antoni Roigé Higueras
 *
 * Es crea la capa de repositori mitjançant JPA.
 * Es mapeja la capa Book i li indiquem que la PK de
 * la taula sera del tipus Long
 */

public interface BookRepository extends JpaRepository<Book, Long> {

    Void save(Optional<Book> bookToUpdate);
}
