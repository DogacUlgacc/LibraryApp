package com.grup_7.LibraryApp.dto.bookDto.request;

import com.grup_7.LibraryApp.enums.book.BookStatus;

public class BookStatusUpdateRequestDto {

    private BookStatus status;

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
