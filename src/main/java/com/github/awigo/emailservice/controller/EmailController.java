package com.github.awigo.emailservice.controller;

import com.github.awigo.emailservice.model.Email;
import com.github.awigo.emailservice.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/email/{id}")
    public ResponseEntity<Email> getById(@PathVariable Long id) {
        Email email = emailService.getById(id);
        return new ResponseEntity<>(email, HttpStatus.OK);
    }

    @PostMapping("/email")
    public ResponseEntity<Long> addEmail(@RequestBody Email email) {
        Long emailId = emailService.addEmail(email);
        return new ResponseEntity<>(emailId, HttpStatus.CREATED);
    }

    @PutMapping("/email/{id}")
    public ResponseEntity<Email> updateEmail(@PathVariable Long id, @RequestBody Email email) {
        Email updated = emailService.updateById(id, email);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/email/{id}")
    public ResponseEntity<Email> deleteById(@PathVariable Long id) {
        Email deleted = emailService.deleteById(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
