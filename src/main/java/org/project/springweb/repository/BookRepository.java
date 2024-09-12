package org.project.springweb.repository;

import org.project.springweb.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> getAll();

    Optional<Book> getBookById(Long id);

    Book create(Book book);
}
