package com.github.awigo.emailservice.service;

import com.github.awigo.emailservice.exceptions.UserNotFoundException;
import com.github.awigo.emailservice.model.Email;
import com.github.awigo.emailservice.model.EmailAddress;
import com.github.awigo.emailservice.repository.EmailAddressRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    private final EmailAddressRepository emailAddressRepository;

    private final JavaMailSender emailSender;

    public EmailService(EmailAddressRepository emailAddressRepository, JavaMailSender emailSender) {
        this.emailAddressRepository = emailAddressRepository;
        this.emailSender = emailSender;
    }

    public void sendEmailToAll(Email email) {
        List<EmailAddress> emailAddresses = emailAddressRepository.findAll();
        emailAddresses.forEach(emailAddress -> {
            email.setTo(emailAddress.getAddress());
            sendEmail(email);
        });
    }

    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getMessage());
        emailSender.send(message);
    }

    public EmailAddress getById(Long id) {
        return emailAddressRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("Address with id %d not found in repository", id)));
    }

    public Long addEmailAddress(EmailAddress email) {
        EmailAddress saved = emailAddressRepository.save(email);
        return saved.getId();
    }

    public EmailAddress updateById(Long id, EmailAddress email) {
        EmailAddress toUpdate = emailAddressRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("Address with id %d not found in repository", id)));
        toUpdate.setAddress(email.getAddress());
        emailAddressRepository.save(toUpdate);
        return toUpdate;
    }

    public EmailAddress deleteById(Long id) {
        EmailAddress toDelete = emailAddressRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("Address with id %d not found in repository", id)));
        emailAddressRepository.delete(toDelete);
        return toDelete;
    }

    public List<EmailAddress> getAll() {
        return emailAddressRepository.findAll();
    }
}
