package com.example.specdbms7.repository;

import com.example.specdbms7.entity.Reader;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author artem
 * @version: 1.0.0
 * @project SpecDBMS_7
 * @date 25.05.2022 22:01
 * @class IRecordRepository
 */
@Repository
public interface IRecordRepository extends Neo4jRepository<Reader, Long> {
}
