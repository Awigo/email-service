package com.github.awigo.emailservice.model;

import lombok.Data;

@Data
public class Email {
    private String from;
    private String to;
    private final String subject;
    private final String message;
}
