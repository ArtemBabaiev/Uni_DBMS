package com.example.specdbms7.controller;

import com.example.specdbms7.entity.Employee;
import com.example.specdbms7.repository.IEmployeeRepository;
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
 * @date 25.05.2022 22:49
 * @class EmployeeRestController
 */

@RestController
@RequestMapping("api/employees")
public class EmployeeRestController {
    @Autowired
    IEmployeeRepository repository;

    @GetMapping("/inLibrary/{number}")
    List<Employee> showWorkersInLibrary(@PathVariable int number){
        return repository.getEmployeeByLibraryNumber(number);
    }

}
