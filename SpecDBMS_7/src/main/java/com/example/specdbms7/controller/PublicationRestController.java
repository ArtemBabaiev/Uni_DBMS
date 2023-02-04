package com.example.specdbms7.controller;

import com.example.specdbms7.entity.Employee;
import com.example.specdbms7.entity.Publication;
import com.example.specdbms7.repository.IPublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project SpecDBMS_7
 * @date 25.05.2022 23:22
 * @class PublicationRestController
 */
@RestController
@RequestMapping("api/publications")
public class PublicationRestController {
    @Autowired
    IPublicationRepository repository;

    @GetMapping("/lendedBy/{id}")
    List<Publication> showLendedPublicationByUser(@PathVariable Long id){
        return repository.getPublicationIfLendedByUser(id);
    }
}
