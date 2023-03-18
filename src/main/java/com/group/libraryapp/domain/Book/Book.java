package com.group.libraryapp.domain.Book;


import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false, length = 255, name = "name")
    private String name;

    public Book() {
    }

    public Book(String name) {
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)가 들어왔습니다"));
        }
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
