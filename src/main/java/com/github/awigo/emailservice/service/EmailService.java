package com.github.awigo.emailservice.service;

import com.github.awigo.emailservice.exceptions.UserNotFoundException;
import com.github.awigo.emailservice.model.Email;
import com.github.awigo.emailservice.repository.EmailRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender emailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender emailSender) {
        this.emailRepository = emailRepository;
        this.emailSender = emailSender;
    }

    public void sendEmail(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
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
