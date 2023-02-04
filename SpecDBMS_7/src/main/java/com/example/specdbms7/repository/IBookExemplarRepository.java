package com.example.specdbms7.repository;

import com.example.specdbms7.entity.BookExemplar;
import com.example.specdbms7.entity.Reader;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project SpecDBMS_7
 * @date 25.05.2022 21:45
 * @class IBookExemplarRepository
 */
@Repository
public interface IBookExemplarRepository extends Neo4jRepository<BookExemplar, Long> {
    @Query("match (b:BookExemplar)<-[:lendedWhat]-(:Record) return b")
    List<BookExemplar> getBookExemplarIfLended();

    @Query("match (p:Publisher)-[:publishes]->(:Publication)<-[:exemplarOf]-(b:BookExemplar) where p.name = $publisher return b")
    List<BookExemplar> getBookExemplarByPublisher( String publisher);

    @Query("match (r:Record)-[:lendedWhat]->(b:BookExemplar{nomenclatureNumber: $nomenclatureNumber })-[:exemplarOf]->(p:Publication) " +
            "create (r)-[:lendedWhat]->(w:WrittenOfExemplar{dateOfWriteOff:date(), nomenclatureNumber: b.nomenclatureNumber})-[:exemplarOf]->(p) " +
            "set p.numberInStock = p.numberInStock-1 " +
            "detach delete b")
    void WriteOffBookExemplar(String nomenclatureNumber);
}
