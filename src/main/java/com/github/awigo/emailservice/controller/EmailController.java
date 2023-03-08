package com.github.awigo.emailservice.controller;

import com.github.awigo.emailservice.model.Email;
import com.github.awigo.emailservice.model.EmailAddress;
import com.github.awigo.emailservice.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @ResponseBody
    @PostMapping("/send-email-to-all")
    public HttpStatus sendEmail(@RequestBody Email email) {
        emailService.sendEmailToAll(email);
        return HttpStatus.OK;
    }

    @GetMapping("/email/all")
    public ResponseEntity<List<EmailAddress>> getAll() {
        List<EmailAddress> emailAddressList = emailService.getAll();
        return new ResponseEntity<>(emailAddressList, HttpStatus.OK);
    }

    @GetMapping("/email/{id}")
    public ResponseEntity<EmailAddress> getById(@PathVariable Long id) {
        EmailAddress email = emailService.getById(id);
        return new ResponseEntity<>(email, HttpStatus.OK);
    }

    @PostMapping("/email")
    public ResponseEntity<Long> addEmailAddress(@RequestBody EmailAddress emailAddress) {
        Long emailId = emailService.addEmailAddress(emailAddress);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(emailId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/email/{id}")
    public ResponseEntity<EmailAddress> updateEmail(@PathVariable Long id, @RequestBody EmailAddress email) {
        EmailAddress updated = emailService.updateById(id, email);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/email/{id}")
    public ResponseEntity<EmailAddress> deleteById(@PathVariable Long id) {
        EmailAddress deleted = emailService.deleteById(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
