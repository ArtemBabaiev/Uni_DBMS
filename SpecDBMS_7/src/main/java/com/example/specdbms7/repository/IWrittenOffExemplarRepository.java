package com.example.specdbms7.repository;

import com.example.specdbms7.entity.WrittenOffExemplar;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author artem
 * @version: 1.0.0
 * @project SpecDBMS_7
 * @date 25.05.2022 21:53
 * @class IRecordWrittenOfExemplar
 */
@Repository
public interface IWrittenOffExemplarRepository extends Neo4jRepository<WrittenOffExemplar, Long> {
}
