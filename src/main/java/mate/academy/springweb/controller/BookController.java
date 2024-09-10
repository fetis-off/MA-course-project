package mate.academy.springweb.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springweb.dto.BookDto;
import mate.academy.springweb.dto.CreateBookRequestDto;
import mate.academy.springweb.service.BookService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<BookDto> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public BookDto createBook(@RequestBody CreateBookRequestDto requestDto) {
        return bookService.create(requestDto);
    }
}
