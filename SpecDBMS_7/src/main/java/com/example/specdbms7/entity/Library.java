package com.example.specdbms7.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Objects;

/**
 * @author artem
 * @version: 1.0.0
 * @project SpecDBMS_7
 * @date 25.05.2022 12:23
 * @class Library
 */
@Node("Library")
public class Library {
    @Id
    @GeneratedValue
    Long id;
    int number;
    String location;
    int roomsQuantity;

    public Library() {
    }

    public Library(int number, String location, int roomsQuantity) {
        this.number = number;
        this.location = location;
        this.roomsQuantity = roomsQuantity;
    }

    public Library(Long id, int number, String location, int roomsQuantity) {
        this.id = id;
        this.number = number;
        this.location = location;
        this.roomsQuantity = roomsQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRoomsQuantity() {
        return roomsQuantity;
    }

    public void setRoomsQuantity(int roomsQuantity) {
        this.roomsQuantity = roomsQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(id, library.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", number=" + number +
                ", location='" + location + '\'' +
                ", roomsQuantity=" + roomsQuantity +
                '}';
    }


}
