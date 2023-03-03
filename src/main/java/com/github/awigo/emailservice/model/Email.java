package com.github.awigo.emailservice.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Entity(name = "email")
@Table(name = "email")
@EqualsAndHashCode
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
