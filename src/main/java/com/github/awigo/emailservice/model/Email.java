package com.github.awigo.emailservice.model;

import lombok.Data;

@Data
public class Email {
    private final String from;
    private final String to;
    private final String subject;
    private final String message;
}
