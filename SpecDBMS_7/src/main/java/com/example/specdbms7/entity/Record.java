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
 * @date 25.05.2022 12:31
 * @class Record
 */
@Node("Record")
public class Record {
    @Id
    @GeneratedValue
    Long id;
    LocalDateTime lendedAt;
    LocalDateTime returnedAt;

    @Relationship(type = "lendedBy", direction = Relationship.Direction.OUTGOING)
    Set<Reader> readers = new HashSet<>();
    @Relationship(type = "lendedWhat", direction = Relationship.Direction.OUTGOING)
    Set<BookExemplar> bookExemplars = new HashSet<>();
    @Relationship(type = "lendedWhat", direction = Relationship.Direction.OUTGOING)
    Set<WrittenOffExemplar> writtenOfExemplars = new HashSet<>();
    @Relationship(type = "givenBy", direction = Relationship.Direction.OUTGOING)
    Set<Employee> employeesGiv = new HashSet<>();
    @Relationship(type = "acceptedBy", direction = Relationship.Direction.OUTGOING)
    Set<Employee> employeesAcc = new HashSet<>();

    public Record() {
    }

    public Record(LocalDateTime lendedAt, LocalDateTime returnedAt) {
        this.lendedAt = lendedAt;
        this.returnedAt = returnedAt;
    }

    public Record(Long id, LocalDateTime lendedAt, LocalDateTime returnedAt) {
        this.id = id;
        this.lendedAt = lendedAt;
        this.returnedAt = returnedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLendedAt() {
        return lendedAt;
    }

    public void setLendedAt(LocalDateTime lendedAt) {
        this.lendedAt = lendedAt;
    }

    public LocalDateTime getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(LocalDateTime returnedAt) {
        this.returnedAt = returnedAt;
    }

    public Set<Reader> getReaders() {
        return readers;
    }

    public void setReaders(Set<Reader> readers) {
        this.readers = readers;
    }

    public Set<BookExemplar> getBookExemplars() {
        return bookExemplars;
    }

    public void setBookExemplars(Set<BookExemplar> bookExemplars) {
        this.bookExemplars = bookExemplars;
    }

    public Set<WrittenOffExemplar> getWrittenOfExemplars() {
        return writtenOfExemplars;
    }

    public void setWrittenOfExemplars(Set<WrittenOffExemplar> writtenOfExemplars) {
        this.writtenOfExemplars = writtenOfExemplars;
    }

    public Set<Employee> getEmployeesGiv() {
        return employeesGiv;
    }

    public void setEmployeesGiv(Set<Employee> employeesGiv) {
        this.employeesGiv = employeesGiv;
    }

    public Set<Employee> getEmployeesAcc() {
        return employeesAcc;
    }

    public void setEmployeesAcc(Set<Employee> employeesAcc) {
        this.employeesAcc = employeesAcc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return id.equals(record.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", lendedAt=" + lendedAt +
                ", returnedAt=" + returnedAt +
                '}';
    }
}
