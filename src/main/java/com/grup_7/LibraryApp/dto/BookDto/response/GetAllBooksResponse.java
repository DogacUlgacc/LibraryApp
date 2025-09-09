package com.grup_7.LibraryApp.dto.BookDto.response;

import com.grup_7.LibraryApp.entity.Author;
import com.grup_7.LibraryApp.entity.Category;
import com.grup_7.LibraryApp.entity.Publisher;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class GetAllBooksResponse {

    private String title;
    private List<Author> author;
    private int availableCopies;
    private Category category;
    private Publisher publisher;


}