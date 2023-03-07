package com.github.awigo.emailservice.service;

import com.github.awigo.emailservice.model.Email;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public Email getById(Long id) {
        Email email = new Email();
        email.setId(id);
        email.setAddress("bob@gmail.com");
        return email;
    }

    public Long addEmail(Email email) {
        return null;
    }

    public Long updateById(Long id, Email email) {
        return null;
    }

    public Long deleteById(Long id) {
        return null;
    }
}
