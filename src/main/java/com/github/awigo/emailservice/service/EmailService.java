package com.github.awigo.emailservice.service;

import com.github.awigo.emailservice.exceptions.UserNotFoundException;
import com.github.awigo.emailservice.model.Email;
import com.github.awigo.emailservice.repository.EmailRepository;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public Email getById(Long id) {
        return emailRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found in repository", id)));
    }

    public Long addEmail(Email email) {
        Email saved = emailRepository.save(email);
        return saved.getId();
    }

    public Email updateById(Long id, Email email) {
        Email toUpdate = emailRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found in repository", id)));
        toUpdate.setAddress(email.getAddress());
        emailRepository.save(toUpdate);
        return toUpdate;
    }

    public Email deleteById(Long id) {
        Email toDelete = emailRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found in repository", id)));
        emailRepository.delete(toDelete);
        return toDelete;
    }
}
