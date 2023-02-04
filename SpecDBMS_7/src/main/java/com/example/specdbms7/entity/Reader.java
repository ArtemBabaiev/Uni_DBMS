package com.example.specdbms7.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author artem
 * @version: 1.0.0
 * @project SpecDBMS_7
 * @date 25.05.2022 12:30
 * @class Reader
 */
@Node("Reader")
public class Reader {
    @Id
    @GeneratedValue
    Long id;
    String fullName;
    String ocupation;
    String subscriptionNumber;
    LocalDate registrationDate;
    String residenceArea;
    LocalDate birthDate;

    public Reader() {
    }

    public Reader(String fullName, String ocupation, String subscriptionNumber, LocalDate registrationDate, String residenceArea, LocalDate birthDate) {
        this.fullName = fullName;
        this.ocupation = ocupation;
        this.subscriptionNumber = subscriptionNumber;
        this.registrationDate = registrationDate;
        this.residenceArea = residenceArea;
        this.birthDate = birthDate;
    }

    public Reader(Long id, String fullName, String ocupation, String subscriptionNumber, LocalDate registrationDate, String residenceArea, LocalDate birthDate) {
        this.id = id;
        this.fullName = fullName;
        this.ocupation = ocupation;
        this.subscriptionNumber = subscriptionNumber;
        this.registrationDate = registrationDate;
        this.residenceArea = residenceArea;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOcupation() {
        return ocupation;
    }

    public void setOcupation(String ocupation) {
        this.ocupation = ocupation;
    }

    public String getSubscriptionNumber() {
        return subscriptionNumber;
    }

    public void setSubscriptionNumber(String subscriptionNumber) {
        this.subscriptionNumber = subscriptionNumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getResidenceArea() {
        return residenceArea;
    }

    public void setResidenceArea(String residenceArea) {
        this.residenceArea = residenceArea;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return id.equals(reader.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", ocupation='" + ocupation + '\'' +
                ", subscriptionNumber='" + subscriptionNumber + '\'' +
                ", registrationDate=" + registrationDate +
                ", residenceArea='" + residenceArea + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
