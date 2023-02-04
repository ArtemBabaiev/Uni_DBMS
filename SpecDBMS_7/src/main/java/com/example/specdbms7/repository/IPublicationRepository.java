package com.example.specdbms7.repository;

import com.example.specdbms7.entity.Publication;
import com.example.specdbms7.entity.Reader;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project SpecDBMS_7
 * @date 25.05.2022 21:51
 * @class IPublicationRepository
 */
@Repository
public interface IPublicationRepository extends Neo4jRepository<Publication, Long> {

    @Query("match (r:Reader)<-[:lendedBy]-(:Record)-[:lendedWhat]->(:BookExemplar)-[:exemplarOf]->(p:Publication) where ID(r) = 43 return p")
    List<Publication> getPublicationIfLendedByUser(Long id);
}
