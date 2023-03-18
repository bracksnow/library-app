package com.group.libraryapp.service.Book;

import com.group.libraryapp.domain.Book.Book;
import com.group.libraryapp.domain.Book.BookRepository;
import com.group.libraryapp.domain.User.User;
import com.group.libraryapp.domain.User.UserRepository;
import com.group.libraryapp.domain.User.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.User.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.Book.BookCreateRequest;
import com.group.libraryapp.dto.Book.BookLoanRequest;
import com.group.libraryapp.dto.Book.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;


    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request){
        bookRepository.save(new Book(request.getName()));

    }

    @Transactional
    public void loanBook(BookLoanRequest request){
        Book book = bookRepository.findByName(request.getBookName()).orElseThrow(IllegalAccessError::new);

        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)){
            throw new IllegalArgumentException("대출되어 있는 책입니다.");
        }

        User user = userRepository.findByName(request.getUserName());
        user.loanBook(book.getName());

        if(user == null){
            throw new IllegalArgumentException();
        }

        userLoanHistoryRepository.save(new UserLoanHistory(user, book.getName(), false));
    }

    @Transactional
    public void returnBook(BookReturnRequest request){
        User user = userRepository.findByName(request.getUserName());

        if(user == null){
            throw new IllegalArgumentException();
        }

        UserLoanHistory history = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        history.doReturn();
        // Transactional로 인해 변경감지가 되었고 JPA는 자동으로 DB에 저장
    }

}
