package com.example.specdbms7.controller;

import com.example.specdbms7.entity.BookExemplar;
import com.example.specdbms7.entity.Reader;
import com.example.specdbms7.repository.IBookExemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project SpecDBMS_7
 * @date 25.05.2022 22:06
 * @class BookExemplarRestController
 */
@RestController
@RequestMapping("api/bookExemplars")
public class BookExemplarRestController {
    @Autowired
    IBookExemplarRepository repository;

    @GetMapping("/lended")
    List<BookExemplar> showLendedBooks(){
        return repository.getBookExemplarIfLended();
    }

    @GetMapping("/{publisher}")
    List<BookExemplar> showBooksByPublisher(@PathVariable String publisher){
        return repository.getBookExemplarByPublisher(publisher);
    }

    @DeleteMapping("/{number}")
    void writeOff(@PathVariable String number){
        System.out.println("delete");
        repository.WriteOffBookExemplar(number);
    }

    @GetMapping("/")
    public List<BookExemplar> showAll() {
        return repository.findAll();
    }
}
