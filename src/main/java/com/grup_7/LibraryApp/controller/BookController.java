package com.grup_7.LibraryApp.controller;

import com.grup_7.LibraryApp.dto.BookDto.request.BookForAddDto;
import com.grup_7.LibraryApp.dto.BookDto.response.GetAllBooksResponse;
import com.grup_7.LibraryApp.dto.BookDto.response.GetBookForIdResponse;
import com.grup_7.LibraryApp.entity.Books;
import com.grup_7.LibraryApp.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public List<GetAllBooksResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    // TODO:: check book
    @GetMapping("/{id}")
    public GetBookForIdResponse getBookById(@RequestParam int id) {
       return bookService.getBookForId(id);

    }

    @PostMapping("/add")
    public BookForAddDto addBook(@RequestBody BookForAddDto bookForAddDto) {
        return bookService.saveBook(bookForAddDto);
    }
}
