package com.example.specdbms7;

import com.example.specdbms7.entity.BookExemplar;
import com.example.specdbms7.entity.Reader;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;

import java.util.List;


@SpringBootApplication
public class SpecDbms7Application {




    public static void main(String[] args) {
        SpringApplication.run(SpecDbms7Application.class, args);
        /*Neo4jTemplate neo4jTemplate = new Neo4jTemplate(
                Neo4jClient.create(
                        GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "123"))));*/

        /*System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        List<BookExemplar> bookExemplars = neo4jTemplate.findAll("MATCH (n: BookExemplar) RETURN n", BookExemplar.class);
        for (BookExemplar bookExemplar : bookExemplars) {
            System.out.println(bookExemplar);
        }*/
    }



}
