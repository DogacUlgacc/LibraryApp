package com.grup_7.LibraryApp.dto.BookDto.response;

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
public class GetBookForIdResponse {

    private String title;
    private List<Author> author;
    private int availableCopies;
    private Category category;
    private Publisher publisher;


}