package com.github.awigo.emailservice.service;

import com.github.awigo.emailservice.model.Email;
import com.github.awigo.emailservice.repository.EmailRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailService {

    private final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public Email getById(Long id) {
        return emailRepository
                .findById(id)
                .orElse(null);
    }

    public Long addEmail(Email email) {
        Email saved = emailRepository.save(email);
        return saved.getId();
    }

    public Email updateById(Long id, Email email) {
        Email toUpdate = emailRepository.findById(id).get();
        toUpdate.setAddress(email.getAddress());
        emailRepository.save(toUpdate);
        return toUpdate;
    }

    public Long deleteById(Long id) {
        return null;
    }
}
