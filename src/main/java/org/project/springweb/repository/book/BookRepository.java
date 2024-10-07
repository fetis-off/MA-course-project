package org.project.springweb.repository.book;

import org.project.springweb.dto.book.BookDto;
import org.project.springweb.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<BookDto> {
}
