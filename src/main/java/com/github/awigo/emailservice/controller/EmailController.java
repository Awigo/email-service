package com.github.awigo.emailservice.controller;

import com.github.awigo.emailservice.model.Email;
import com.github.awigo.emailservice.service.EmailService;

public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    public Email getById(Long id) {
        return emailService.getById(id);
    }
}
