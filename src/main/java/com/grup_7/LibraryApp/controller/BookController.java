package com.grup_7.LibraryApp.controller;

import com.grup_7.LibraryApp.dto.bookDto.request.CreateBookRequest;
import com.grup_7.LibraryApp.dto.bookDto.request.BookUpdateDtoRequest;
import com.grup_7.LibraryApp.dto.bookDto.response.BookUpdateDtoResponse;
import com.grup_7.LibraryApp.dto.bookDto.response.CreatedBookResponse;
import com.grup_7.LibraryApp.dto.bookDto.response.GetAllBooksDtoResponse;
import com.grup_7.LibraryApp.dto.bookDto.response.GetBookByIdDtoResponse;
import com.grup_7.LibraryApp.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    private final BookService bookService;

    @GetMapping("/all")
    public List<GetAllBooksDtoResponse> getAllBooks() {
        return bookService.getAllBooks();
    }
    @PutMapping("/{id}")
    public BookUpdateDtoResponse updateBook(@PathVariable int id,
                                            @RequestBody BookUpdateDtoRequest request) {
        return bookService.updateBook(id, request);
    }

    // TODO:: check book
    @GetMapping("/{id}")
    public GetBookByIdDtoResponse getBookById(@PathVariable int id) {
        return bookService.getBookByIdDtoResponse(id);
    }

    @PostMapping("/add")
    public CreatedBookResponse addBook(@RequestBody CreateBookRequest request) {
        return bookService.addBook(request);
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
    }
}