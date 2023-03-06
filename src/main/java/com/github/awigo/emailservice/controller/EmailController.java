package com.github.awigo.emailservice.controller;

import com.github.awigo.emailservice.model.Email;

public class EmailController {
    public Email getById(Long id) {
        Email email = new Email();
        email.setAddress("jon@gmail.com");
        return email;
    }
}
