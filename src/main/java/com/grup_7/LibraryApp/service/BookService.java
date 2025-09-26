package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.core.exception.type.BusinessException;
import com.grup_7.LibraryApp.dto.bookDto.request.BookStatusUpdateRequestDto;
import com.grup_7.LibraryApp.dto.bookDto.request.CreateBookRequest;
import com.grup_7.LibraryApp.dto.bookDto.request.BookUpdateDtoRequest;
import com.grup_7.LibraryApp.dto.bookDto.response.*;
import com.grup_7.LibraryApp.entity.Author;
import com.grup_7.LibraryApp.entity.Book;
import com.grup_7.LibraryApp.entity.Category;
import com.grup_7.LibraryApp.entity.Publisher;
import com.grup_7.LibraryApp.enums.book.BookStatus;
import com.grup_7.LibraryApp.mapper.BookMapper;
import com.grup_7.LibraryApp.repository.AuthorRepository;
import com.grup_7.LibraryApp.repository.BookRepository;
import com.grup_7.LibraryApp.repository.CategoryRepository;
import com.grup_7.LibraryApp.repository.PublisherRepository;
import com.grup_7.LibraryApp.rules.BookBusinessRules;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;
    private final BookBusinessRules bookBusinessRules;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, PublisherRepository publisherRepository, BookBusinessRules bookBusinessRules, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.publisherRepository = publisherRepository;
        this.bookBusinessRules = bookBusinessRules;
        this.bookMapper = bookMapper;
    }

    public List<GetAllBooksDtoResponse> getAllBooks() {
        return bookMapper.toGetAllBooksDtoResponse(bookRepository.findAll());
       /* return bookRepository.findAll()
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
                .toList();*/
    }

    public CreatedBookResponse addBook(CreateBookRequest request) {
        bookBusinessRules.isbnMustUnique(request.getIsbn());
        bookBusinessRules.totalCopiesMustBeNonNegative(request.getTotalCopies());

        Book book = new Book();

        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn()); // <- burayı ekle

        if (request.getPublishYear() != null) book.setPublishYear(request.getPublishYear());
        if (request.getTotalCopies() != null) book.setTotalCopies(request.getTotalCopies());
        book.setAvailableCopies(request.getTotalCopies() != null ? request.getTotalCopies() : 0);

        if (request.getAuthorId() == null) throw new RuntimeException("Yazar zorunlu.");
        Author author = authorRepository.findById((request.getAuthorId())).orElseThrow(() -> new RuntimeException("Yazar bulunamadı: " + request.getAuthorId()));
        book.setAuthors(List.of(author));

        if (request.getCategoryId() == null) throw new RuntimeException("Kategori zorunlu.");
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new RuntimeException("Kategori bulunamadı: " + request.getCategoryId()));
        book.setCategory(category);

        if (request.getPublisherId() == null) throw new RuntimeException("Yayınevi zorunlu.");
        Publisher publisher = publisherRepository.findById(request.getPublisherId()).orElseThrow(() -> new RuntimeException("Yayınevi bulunamadı: " + request.getPublisherId()));
        book.setPublisher(publisher);

        Book saved = bookRepository.save(book);

        return bookMapper.toCreatedBookResponse(saved);
    }

    public GetBookByIdDtoResponse getBookByIdDtoResponse(int id) {
        Book book = bookBusinessRules.bookShouldExistWithGivenId(id);
        return bookMapper.toGetBookByIdDtoResponse(book);
    }

    public BookUpdateDtoResponse updateBook(int id, BookUpdateDtoRequest req) {
        Book book = bookBusinessRules.bookShouldExistWithGivenId(id);
        bookBusinessRules.availableCopiesMustBeValid(req.getAvailableCopies(), req.getTotalCopies());
        bookBusinessRules.totalCopiesMustBeNonNegative(req.getTotalCopies());

        if (req.getTitle() != null && !req.getTitle().isBlank()) book.setTitle(req.getTitle());
        if (req.getPublishYear() != null) book.setPublishYear(req.getPublishYear());
        if (req.getTotalCopies() != null) book.setTotalCopies(req.getTotalCopies());
        if (req.getAvailableCopies() != null) book.setAvailableCopies(req.getAvailableCopies());

        if (req.getAuthorId() != null) {
            Author author = authorRepository.findById((req.getAuthorId())).orElseThrow(() -> new BusinessException("Yazar bulunamadı: " + req.getAuthorId()));
            book.setAuthors(new ArrayList<>(List.of(author)));

        }

        if (req.getCategoryId() != null) {
            Category category = categoryRepository.findById(req.getCategoryId()).orElseThrow(() -> new BusinessException("Kategori bulunamadı: " + req.getCategoryId()));
            book.setCategory(category);
        }

        if (req.getPublisherId() != null) {
            Publisher publisher = publisherRepository.findById(req.getPublisherId()).orElseThrow(() -> new BusinessException("Yayınevi bulunamadı: " + req.getPublisherId()));
            book.setPublisher(publisher);
        }

        Book saved = bookRepository.save(book);

        return bookMapper.toBookUpdateDtoResponse(saved);
//        return new BookUpdateDtoResponse(saved.getTitle(), saved.getAuthors().stream().map(a -> new BookAuthorDto(a.getName(), a.getSurname())).toList(), saved.getAvailableCopies(), new BookCategoryDto(saved.getCategory().getName()), new BookPublisherDto(saved.getPublisher().getName(), saved.getPublisher().getAddress()));
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public BookStatusUpdatedResponse updateBookStatus(int id, BookStatusUpdateRequestDto statusUpdateRequestDto) {
        Book bookForUpdateStatus = bookBusinessRules.bookShouldExistWithGivenId(id);
        bookForUpdateStatus.setStatus(statusUpdateRequestDto.getStatus());
        Book updatedBook = bookRepository.save(bookForUpdateStatus);

        return bookMapper.toBookStatusUpdatedResponse(updatedBook);
        
    }

    public BookCopiesUpdatedResponse updateBookCopies(int id, int delta) {
        Book bookForUpdateCopies = bookBusinessRules.bookShouldExistWithGivenId(id);

        bookBusinessRules.totalCopiesMustBeNonNegative(bookForUpdateCopies.getTotalCopies() + delta);

        bookForUpdateCopies.setTotalCopies(bookForUpdateCopies.getTotalCopies() + delta);
        Book updatedBook = bookRepository.save(bookForUpdateCopies);

        return bookMapper.toBookCopiesUpdatedResponse(updatedBook);
    }

    public List<GetAllBooksDtoResponse> getBooks(String isbn, String title, List<Author> authors, BookStatus status) {
        List<Book> books = bookRepository.findAll();

        if (isbn != null && !isbn.isEmpty()) {
            books = books.stream()
                    .filter(b -> b.getIsbn().equalsIgnoreCase(isbn))
                    .collect(Collectors.toList());
        }

        if (title != null && !title.isEmpty()) {
            books = books.stream()
                    .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (authors != null && !authors.isEmpty()) {
            books = books.stream()
                    .filter(b -> b.getAuthors().stream()
                            .anyMatch(a -> authors.stream()
                                    .anyMatch(f -> f.getId() == a.getId())))
                    .collect(Collectors.toList());
        }

        if (status != null) {
            books = books.stream()
                    .filter(b -> b.getStatus() == status)
                    .collect(Collectors.toList());
        }

        // **Mapper'ın List metodunu kullan** → hatasız
        return bookMapper.toGetAllBooksDtoResponse(books);
    }


}
