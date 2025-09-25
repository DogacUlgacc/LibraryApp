package com.grup_7.LibraryApp.rules;

import com.grup_7.LibraryApp.core.exception.type.BusinessException;
import com.grup_7.LibraryApp.entity.Book;
import com.grup_7.LibraryApp.entity.Member;
import com.grup_7.LibraryApp.enums.book.BookStatus;
import com.grup_7.LibraryApp.repository.BookRepository;
import org.springframework.stereotype.Component;


@Component
public class BookBusinessRules {

    private final BookRepository bookRepository;

    public BookBusinessRules(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book bookShouldExistWithGivenId(int id){
        return bookRepository.findById(id).orElseThrow(()-> new BusinessException("Bu id ile bir kitap bulunamadı"));
    }

    public void isbnMustUnique(String isbn) {
        Book bookWithSameIsbn = bookRepository.findTop1ByIsbnIgnoreCase(isbn).orElse(null);
        if (bookWithSameIsbn != null) {
            throw new BusinessException("Bu isbn ile daha önce kayıt oluşturulmuştur.");
        }
    }

    public void totalCopiesMustBeNonNegative(int totalCopies) {
        if (totalCopies < 0) {
            throw new BusinessException("Toplam kopya sayısı 0'dan küçük olamaz.");
        }
    }

    public void availableCopiesMustBeValid(int availableCopies, int totalCopies) {
        if (availableCopies < 0) {
            throw new BusinessException("Mevcut kopya sayısı negatif olamaz.");
        }
        if (availableCopies > totalCopies) {
            throw new BusinessException("Mevcut kopya sayısı toplam kopya sayısını aşamaz.");
        }
    }

    //TODO:: kitap durumunu kontrol methodu hiç kullanılmamış loan service'de kontrol edilecek!!!!!
    public void bookMustBeActive(Book book) {
        if (book.getStatus() == BookStatus.INACTIVE) {
            throw new BusinessException("Kitap INACTIVE olduğu için ödünç verilemez veya rezervasyon alınamaz.");
        }
    }



}
