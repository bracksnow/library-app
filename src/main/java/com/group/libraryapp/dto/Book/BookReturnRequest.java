package com.group.libraryapp.dto.Book;

public class BookReturnRequest {
    private String userName;
    private String bookName;

    public BookReturnRequest(String userName, String bookName) {
        this.userName = userName;
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }

    public String getUserName() {
        return userName;
    }
}
