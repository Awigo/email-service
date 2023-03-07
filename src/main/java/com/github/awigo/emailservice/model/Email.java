package com.github.awigo.emailservice.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "email")
@Table(name = "email")
@ToString
@EqualsAndHashCode
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;

    public Email() {
    }

    public Email(String address) {
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
