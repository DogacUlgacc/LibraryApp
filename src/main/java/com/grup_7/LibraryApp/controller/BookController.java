package com.grup_7.LibraryApp.controller;

import com.grup_7.LibraryApp.dto.bookDto.request.BookStatusUpdateRequestDto;
import com.grup_7.LibraryApp.dto.bookDto.request.CreateBookRequest;
import com.grup_7.LibraryApp.dto.bookDto.request.BookUpdateDtoRequest;
import com.grup_7.LibraryApp.dto.bookDto.response.*;
import com.grup_7.LibraryApp.entity.Author;
import com.grup_7.LibraryApp.enums.book.BookStatus;
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
    public BookUpdateDtoResponse updateBook(@PathVariable int id, @RequestBody BookUpdateDtoRequest request) {
        return bookService.updateBook(id, request);
    }

    @PutMapping("{id}/status")
    public BookStatusUpdatedResponse updateBookStatus(@PathVariable int id, BookStatusUpdateRequestDto statusUpdateRequestDto) {
        return bookService.updateBookStatus(id,statusUpdateRequestDto);
    }

    @PatchMapping("/{id}/copies")
    public BookCopiesUpdatedResponse updateBookCopies(@PathVariable int id,@RequestParam (required = false )int delta) {
        return bookService.updateBookCopies(id,delta);
    }


    // TODO:: check book
    @GetMapping("/{id}")
    public GetBookByIdDtoResponse getBookById(@PathVariable int id) {
        return bookService.getBookByIdDtoResponse(id);
    }

   @GetMapping()
   public List<GetAllBooksDtoResponse> getBooks(
           @RequestParam(required = false) String isbn,
           @RequestParam(required = false) String title,
           @RequestParam(required = false) List<Author> authors,
           @RequestParam(required = false) BookStatus status){
        return bookService.getBooks(isbn,title,authors,status);
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