package com.example.specdbms7.controller;

import com.example.specdbms7.entity.Reader;
import com.example.specdbms7.repository.IReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project SpecDBMS_7
 * @date 25.05.2022 14:14
 * @class ReaderRestController
 */
@RestController
@RequestMapping("api/readers")
public class ReaderRestController {
    @Autowired
    private IReaderRepository repository;

    @GetMapping("/")
    public List<Reader> showAll() {
        return repository.findAll();
    }
}
