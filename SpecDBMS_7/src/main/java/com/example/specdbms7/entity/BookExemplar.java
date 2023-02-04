package com.example.specdbms7.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author artem
 * @version: 1.0.0
 * @project SpecDBMS_7
 * @date 25.05.2022 12:29
 * @class BookExemplar
 */
@Node("BookExemplar")
public class BookExemplar {
    @Id
    @GeneratedValue
    Long id;
    String nomenclatureNumber;
    int lendTimeInDays;
    LocalDate deliveryDate;

    @Relationship(type = "exemplarOf", direction = Relationship.Direction.OUTGOING)
    Set<Publication> publications = new HashSet<>();

    @Relationship(type = "storedAt", direction = Relationship.Direction.OUTGOING)
    Set<Library> libraries = new HashSet<>();
    public BookExemplar() {
    }

    public BookExemplar(String nomenclatureNumber, int lendTimeInDays, LocalDate deliveryDate) {
        this.nomenclatureNumber = nomenclatureNumber;
        this.lendTimeInDays = lendTimeInDays;
        this.deliveryDate = deliveryDate;
    }

    public BookExemplar(Long id, String nomenclatureNumber, int lendTimeInDays, LocalDate deliveryDate) {
        this.id = id;
        this.nomenclatureNumber = nomenclatureNumber;
        this.lendTimeInDays = lendTimeInDays;
        this.deliveryDate = deliveryDate;
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

    public int getLendTimeInDays() {
        return lendTimeInDays;
    }

    public void setLendTimeInDays(int lendTimeInDays) {
        this.lendTimeInDays = lendTimeInDays;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Set<Publication> getPublications() {
        return publications;
    }

    public void setPublications(Set<Publication> publications) {
        this.publications = publications;
    }

    public Set<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(Set<Library> libraries) {
        this.libraries = libraries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookExemplar that = (BookExemplar) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BookExemplar{" +
                "id=" + id +
                ", nomenclatureNumber='" + nomenclatureNumber + '\'' +
                ", lendTimeInDays=" + lendTimeInDays +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}
