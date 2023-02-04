package com.example.specdbms7.repository;

import com.example.specdbms7.entity.BookExemplar;
import com.example.specdbms7.entity.Employee;
import com.example.specdbms7.entity.Reader;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project SpecDBMS_7
 * @date 25.05.2022 21:47
 * @class IEmployeeRepository
 */
@Repository
public interface IEmployeeRepository extends Neo4jRepository<Employee, Long> {

    @Query("match (e:Employee)-[:worksAt]->(l:Library {number: $libraryNumber}) return e")
    List<Employee> getEmployeeByLibraryNumber(int libraryNumber);

}
