package com.group.libraryapp.dto.Book;

public class BookLoanRequest {
    private String userName;
    private String bookName;

    public BookLoanRequest(String userName, String bookName) {
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
