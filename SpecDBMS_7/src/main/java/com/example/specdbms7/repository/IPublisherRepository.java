package com.example.specdbms7.repository;

import com.example.specdbms7.entity.Publisher;
import com.example.specdbms7.entity.Reader;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author artem
 * @version: 1.0.0
 * @project SpecDBMS_7
 * @date 25.05.2022 21:52
 * @class IPublisherRepository
 */
@Repository
public interface IPublisherRepository extends Neo4jRepository<Publisher, Long> {
}
