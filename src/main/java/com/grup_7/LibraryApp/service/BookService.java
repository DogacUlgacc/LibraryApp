package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.dto.BookDto.request.BookForAddDto;
import com.grup_7.LibraryApp.dto.BookDto.response.GetAllBooksResponse;
import com.grup_7.LibraryApp.dto.BookDto.response.GetBookForIdResponse;
import com.grup_7.LibraryApp.entity.Books;
import com.grup_7.LibraryApp.entity.Category;
import com.grup_7.LibraryApp.repository.BookRepository;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<GetAllBooksResponse> getAllBooks() {
        List<Books> bookList = bookRepository.findAll();
        List<GetAllBooksResponse> booksResponse = new ArrayList<>();
        for (Books book : bookList) {
            new GetAllBooksResponse(
                    book.getTitle(),
                    book.getAuthors(),
                    book.getAvailableCopies(),
                    book.getCategory(),
                    book.getPublisher()
            );
        }

        return booksResponse;
    }

    public BookForAddDto saveBook(BookForAddDto bookForAddDto) {
        Books book = new Books();
        book.setAuthors(bookForAddDto.getAuthor());
        book.setTitle(bookForAddDto.getTitle());
        book.setPublisher(bookForAddDto.getPublisher());
        book.setTotalCopies(bookForAddDto.getTotalCopies());

        Category category = new Category();
        category.setName(bookForAddDto.getCategory().getName());
        book.setCategory(category);

        return new BookForAddDto(
                book.getTitle(),
                book.getAuthors(),
                book.getPublishYear(),
                book.getCategory(),
                book.getTotalCopies(),
                book.getPublisher()
        );
    }

    public GetBookForIdResponse getBookForId(int id) {
       Books book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
       return new GetBookForIdResponse(
               book.getTitle(),
               book.getAuthors(),
               book.getAvailableCopies(),
               book.getCategory(),
               book.getPublisher()
       );

    }
}
