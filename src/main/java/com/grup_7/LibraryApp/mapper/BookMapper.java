package com.grup_7.LibraryApp.mapper;

import com.grup_7.LibraryApp.dto.bookDto.response.*;
import com.grup_7.LibraryApp.entity.Author;
import com.grup_7.LibraryApp.entity.Book;
import com.grup_7.LibraryApp.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")

public interface BookMapper {

    List<GetAllBooksDtoResponse> toGetAllBooksDtoResponse(List<Book> books);

    BookAuthorDto toBookAuthorDto(Author author);

    BookCategoryDto toBookCategoryDto(Category category);

    GetAllBooksDtoResponse toDto(Book book);


    List<BookAuthorDto> toAuthorDtoList(List<Author> authors);

    GetBookByIdDtoResponse toGetBookByIdDtoResponse(Book book);
    CreatedBookResponse toCreatedBookResponse(Book book);

    BookUpdateDtoResponse toBookUpdateDtoResponse(Book book);

    BookStatusUpdatedResponse toBookStatusUpdatedResponse(Book book);

    BookCopiesUpdatedResponse toBookCopiesUpdatedResponse(Book book);

}
