package com.example.specdbms7.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author artem
 * @version: 1.0.0
 * @project SpecDBMS_7
 * @date 25.05.2022 12:27
 * @class WrittenOfExemplar
 */
@Node("WrittenOfExemplar")
public class WrittenOffExemplar {
    @Id
    @GeneratedValue
    Long id;
    String nomenclatureNumber;
    LocalDateTime dateOfWriteOff;

    @Relationship(type = "exemplarOf", direction = Relationship.Direction.OUTGOING)
    Set<Publication> publications = new HashSet<>();

    public WrittenOffExemplar() {
    }

    public WrittenOffExemplar(String nomenclatureNumber, LocalDateTime dateOfWriteOff) {
        this.nomenclatureNumber = nomenclatureNumber;
        this.dateOfWriteOff = dateOfWriteOff;
    }

    public WrittenOffExemplar(Long id, String nomenclatureNumber, LocalDateTime dateOfWriteOff) {
        this.id = id;
        this.nomenclatureNumber = nomenclatureNumber;
        this.dateOfWriteOff = dateOfWriteOff;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomenclatureNumber() {
        return nomenclatureNumber;
    }

    public void setNomenclatureNumber(String nomenclatureNumber) {
        this.nomenclatureNumber = nomenclatureNumber;
    }

    public LocalDateTime getDateOfWriteOff() {
        return dateOfWriteOff;
    }

    public void setDateOfWriteOff(LocalDateTime dateOfWriteOff) {
        this.dateOfWriteOff = dateOfWriteOff;
    }

    public Set<Publication> getPublications() {
        return publications;
    }

    public void setPublications(Set<Publication> publications) {
        this.publications = publications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrittenOffExemplar that = (WrittenOffExemplar) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "WrittenOfExemplar{" +
                "id=" + id +
                ", nomenclatureNumber='" + nomenclatureNumber + '\'' +
                ", dateOfWriteOff=" + dateOfWriteOff +
                '}';
    }
}
