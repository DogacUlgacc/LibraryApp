package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.dto.bookDto.request.CreateBookRequest;
import com.grup_7.LibraryApp.dto.bookDto.request.BookUpdateDtoRequest;
import com.grup_7.LibraryApp.dto.bookDto.response.*;
import com.grup_7.LibraryApp.entity.Author;
import com.grup_7.LibraryApp.entity.Books;
import com.grup_7.LibraryApp.entity.Category;
import com.grup_7.LibraryApp.entity.Publisher;
import com.grup_7.LibraryApp.repository.AuthorRepository;
import com.grup_7.LibraryApp.repository.BookRepository;
import com.grup_7.LibraryApp.repository.CategoryRepository;
import com.grup_7.LibraryApp.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       CategoryRepository categoryRepository,
                       PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.publisherRepository = publisherRepository;
    }

    public List<GetAllBooksDtoResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> new GetAllBooksDtoResponse(
                        book.getTitle(),
                        book.getAuthors().stream()
                                .map(a -> new BookAuthorDto(a.getName(), a.getSurname()))
                                .toList(),
                        book.getAvailableCopies(),
                        book.getCategory() != null ? new BookCategoryDto(book.getCategory().getName()) : null,
                        book.getPublisher() != null ? new BookPublisherDto(book.getPublisher().getName(), book.getPublisher().getAddress()) : null
                ))
                .toList();
    }

    public CreatedBookResponse addBook(CreateBookRequest request) {
        Books book = new Books();

        book.setTitle(request.getTitle());
        if (request.getPublishYear() != null) book.setPublishYear(request.getPublishYear());
        if (request.getTotalCopies() != null) book.setTotalCopies(request.getTotalCopies());
        book.setAvailableCopies(request.getTotalCopies() != null ? request.getTotalCopies() : 0);

        if (request.getAuthorId() == null) throw new RuntimeException("Yazar zorunlu.");
        Author author = authorRepository.findById((request.getAuthorId()))
                .orElseThrow(() -> new RuntimeException("Yazar bulunamadı: " + request.getAuthorId()));
        book.setAuthors(List.of(author));

        if (request.getCategoryId() == null) throw new RuntimeException("Kategori zorunlu.");
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı: " + request.getCategoryId()));
        book.setCategory(category);

        if (request.getPublisherId() == null) throw new RuntimeException("Yayınevi zorunlu.");
        Publisher publisher = publisherRepository.findById(request.getPublisherId())
                .orElseThrow(() -> new RuntimeException("Yayınevi bulunamadı: " + request.getPublisherId()));
        book.setPublisher(publisher);

        Books saved = bookRepository.save(book);

        return new CreatedBookResponse(
                saved.getTitle(),
                saved.getAuthors().stream()
                        .map(a -> new BookAuthorDto(a.getName(), a.getSurname()))
                        .toList(),
                saved.getAvailableCopies(),
                new BookCategoryDto(saved.getCategory().getName()),
                new BookPublisherDto(saved.getPublisher().getName(), saved.getPublisher().getAddress())
        );
    }

    public GetBookByIdDtoResponse getBookByIdDtoResponse(int id) {
        Books book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı: " + id));

        return new GetBookByIdDtoResponse(
                book.getTitle(),
                book.getAuthors().stream()
                        .map(a -> new BookAuthorDto(a.getName(), a.getSurname()))
                        .toList(),
                book.getAvailableCopies(),
                book.getCategory() != null ? new BookCategoryDto(book.getCategory().getName()) : null,
                book.getPublisher() != null ? new BookPublisherDto(book.getPublisher().getName(), book.getPublisher().getAddress()) : null
        );
    }

    public BookUpdateDtoResponse updateBook(int id, BookUpdateDtoRequest req) {
        Books book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı: " + id));

        if (req.getTitle() != null && !req.getTitle().isBlank()) book.setTitle(req.getTitle());
        if (req.getPublishYear() != null) book.setPublishYear(req.getPublishYear());
        if (req.getTotalCopies() != null) book.setTotalCopies(req.getTotalCopies());
        if (req.getAvailableCopies() != null) book.setAvailableCopies(req.getAvailableCopies());

        if (req.getAuthorId() != null) {
            Author author = authorRepository.findById((req.getAuthorId()))
                    .orElseThrow(() -> new RuntimeException("Yazar bulunamadı: " + req.getAuthorId()));
            book.setAuthors(List.of(author));
        }

        if (req.getCategoryId() != null) {
            Category category = categoryRepository.findById(req.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Kategori bulunamadı: " + req.getCategoryId()));
            book.setCategory(category);
        }

        if (req.getPublisherId() != null) {
            Publisher publisher = publisherRepository.findById(req.getPublisherId())
                    .orElseThrow(() -> new RuntimeException("Yayınevi bulunamadı: " + req.getPublisherId()));
            book.setPublisher(publisher);
        }

        Books saved = bookRepository.save(book);

        return new BookUpdateDtoResponse(
                saved.getTitle(),
                saved.getAuthors().stream()
                        .map(a -> new BookAuthorDto(a.getName(), a.getSurname()))
                        .toList(),
                saved.getAvailableCopies(),
                new BookCategoryDto(saved.getCategory().getName()),
                new BookPublisherDto(saved.getPublisher().getName(), saved.getPublisher().getAddress())
        );
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
