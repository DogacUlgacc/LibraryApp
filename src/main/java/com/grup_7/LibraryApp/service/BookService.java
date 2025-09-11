package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.dto.BookDto.request.CreateBookRequest;
import com.grup_7.LibraryApp.dto.BookDto.request.BookUpdateDtoRequest;
import com.grup_7.LibraryApp.dto.BookDto.response.BookUpdateDtoResponse;
import com.grup_7.LibraryApp.dto.BookDto.response.CreatedBookResponse;
import com.grup_7.LibraryApp.dto.BookDto.response.GetAllBooksDtoResponse;
import com.grup_7.LibraryApp.dto.BookDto.response.GetBookByIdDtoResponse;
import com.grup_7.LibraryApp.entity.Author;
import com.grup_7.LibraryApp.entity.Books;
import com.grup_7.LibraryApp.entity.Category;
import com.grup_7.LibraryApp.entity.Publisher;
import com.grup_7.LibraryApp.repository.BookRepository;

import com.grup_7.LibraryApp.repository.PublisherRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<GetAllBooksDtoResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> new GetAllBooksDtoResponse(
                        book.getTitle(),
                        book.getAuthors(),
                        book.getAvailableCopies(),
                        book.getCategory(),
                        book.getPublisher()
                ))
                .toList(); // Java 16+ için, Java 8 kullanıyorsan .collect(Collectors.toList()) kullan
    }

    public CreatedBookResponse addBook(CreateBookRequest request ) {
        Books book = new Books();
        book.setAuthors(request.getAuthors());
        book.setTitle(request.getTitle());
        book.setPublisher(request.getPublisher());
        book.setTotalCopies(request.getTotalCopies());

        Category category = new Category();
        category.setName(request.getCategory().getName());
        book.setCategory(category);

        return new CreatedBookResponse(
                book.getTitle(),
                book.getAuthors(),
                book.getPublishYear(),
                book.getCategory(),
                book.getTotalCopies(),
                book.getPublisher()
        );
    }

    public GetBookByIdDtoResponse getBookByIdDtoResponse(int id){
        Books book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı: "));

        return new GetBookByIdDtoResponse(
                book.getTitle(),
                book.getAuthors(),
                book.getAvailableCopies(),
                book.getCategory(),
                book.getPublisher()
        );

    }

    public BookUpdateDtoResponse updateBook(int id, BookUpdateDtoRequest req) {
        Books book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı: " + id));

        if (req.getTitle() != null)       book.setTitle(req.getTitle());
        if (req.getPublishYear() != null) book.setPublishYear(req.getPublishYear());
        if (req.getTotalCopies() != null) book.setTotalCopies(req.getTotalCopies());

        // authorId geldiyse: var mı kontrol et, tek yazarı listeye koy
        if (req.getAuthorId() != null) {
            var author = authorRepository.findById(req.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Yazar bulunamadı: " + req.getAuthorId()));
            book.setAuthors(java.util.List.of(author)); // Books.authors => List<Author>
        }

        // categoryId geldiyse: var mı kontrol et, ata
        if (req.getCategoryId() != null) {
            var category = categoryRepository.findById(req.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Kategori bulunamadı: " + req.getCategoryId()));
            book.setCategory(category);
        }

        // publisherId geldiyse: var mı kontrol et, ata
        if (req.getPublisherId() != null) {
            var publisher = publisherRepository.findById(req.getPublisherId())
                    .orElseThrow(() -> new RuntimeException("Yayınevi bulunamadı: " + req.getPublisherId()));
            book.setPublisher(publisher);
        }

        var saved = bookRepository.save(book);

        return new BookUpdateDtoResponse(
                saved.getTitle(),
                saved.getAuthors().stream().map(a -> new AuthorDto(a.getName(), a.getSurname())).toList(),
                saved.getAvailableCopies(),
                new CategoryDto(saved.getCategory().getName()),
                new PublisherDto(saved.getPublisher().getName(), saved.getPublisher().getAddress())
        );
    }

    public void deleteBook(int id) {
        Books book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı."));

        bookRepository.delete(book);
    }
}
