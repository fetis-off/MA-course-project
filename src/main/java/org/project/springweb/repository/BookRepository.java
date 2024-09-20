package org.project.springweb.repository;

import org.project.springweb.dto.BookDto;
import org.project.springweb.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<BookDto> {

}
