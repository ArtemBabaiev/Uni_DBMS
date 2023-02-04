package com.example.specdbms7.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Objects;

/**
 * @author artem
 * @version: 1.0.0
 * @project SpecDBMS_7
 * @date 25.05.2022 12:24
 * @class Publication
 */
@Node("Publication")
public class Publication {
    @Id
    @GeneratedValue
    Long id;
    String name;
    int numberInStock;
    int yearOfPublishing;
    String coverType;

    public Publication() {
    }

    public Publication(String name, int numberInStock, int yearOfPublishing, String coverType) {
        this.name = name;
        this.numberInStock = numberInStock;
        this.yearOfPublishing = yearOfPublishing;
        this.coverType = coverType;
    }

    public Publication(Long id, String name, int numberInStock, int yearOfPublishing, String coverType) {
        this.id = id;
        this.name = name;
        this.numberInStock = numberInStock;
        this.yearOfPublishing = yearOfPublishing;
        this.coverType = coverType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(int numberInStock) {
        this.numberInStock = numberInStock;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberInStock=" + numberInStock +
                ", yearOfPublishing=" + yearOfPublishing +
                ", coverType='" + coverType + '\'' +
                '}';
    }
}
