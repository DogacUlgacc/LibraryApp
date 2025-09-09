package com.grup_7.LibraryApp.dto.BookDto.request;

import com.grup_7.LibraryApp.entity.Author;
import com.grup_7.LibraryApp.entity.Category;
import com.grup_7.LibraryApp.entity.Publisher;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class BookForAddDto {

    private String title;
    private List<Author> author;
    private int publishYear;
    private Category category;
    private int totalCopies;
    private Publisher publisher;
}
