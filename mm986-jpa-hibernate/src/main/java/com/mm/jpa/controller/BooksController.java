package com.mm.jpa.controller;

import com.mm.jpa.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BooksController {

    @GetMapping("/")
    public List<Book> getAllBooks() {
        return Arrays
                .asList(new Book(101,
                        "Softwarte Engineering",
                        "Roger S. Pressman")
                );
    }


}

