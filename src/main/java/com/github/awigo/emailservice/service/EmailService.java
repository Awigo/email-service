package com.github.awigo.emailservice.service;

import com.github.awigo.emailservice.exceptions.UserNotFoundException;
import com.github.awigo.emailservice.model.Email;
import com.github.awigo.emailservice.model.EmailAddress;
import com.github.awigo.emailservice.repository.EmailAddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "Address with id %d not found in repository";
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final EmailAddressRepository emailAddressRepository;
    private final JavaMailSender emailSender;

    public EmailService(EmailAddressRepository emailAddressRepository, JavaMailSender emailSender) {
        this.emailAddressRepository = emailAddressRepository;
        this.emailSender = emailSender;
    }

    public void sendEmailToAll(Email email) {
        logger.info("Sending an email to all email addresses in db");
        List<EmailAddress> emailAddresses = emailAddressRepository.findAll();
        emailAddresses.forEach(emailAddress -> {
            email.setTo(emailAddress.getAddress());
            sendEmail(email);
        });
    }

    public void sendEmail(Email email) {
        logger.info("Sending an email to " + email.getTo());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getMessage());
        emailSender.send(message);
    }

    public EmailAddress getById(Long id) {
        logger.info("Getting an email address by id " + id);
        return emailAddressRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_ERROR_MESSAGE, id)));
    }

    public Long addEmailAddress(EmailAddress email) {
        logger.info("Adding an email address " + email.getAddress() + " to db");
        EmailAddress saved = emailAddressRepository.save(email);
        return saved.getId();
    }

    public EmailAddress updateById(Long id, EmailAddress email) {
        logger.info("Updating an email address with id " + id + " to " + email.getAddress());
        EmailAddress toUpdate = emailAddressRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_ERROR_MESSAGE, id)));
        toUpdate.setAddress(email.getAddress());
        emailAddressRepository.save(toUpdate);
        return toUpdate;
    }

    public EmailAddress deleteById(Long id) {
        logger.info("Deleting an email by id "  + id);
        EmailAddress toDelete = emailAddressRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_ERROR_MESSAGE, id)));
        emailAddressRepository.delete(toDelete);
        return toDelete;
    }

    public List<EmailAddress> getAll() {
        logger.info("Getting all email addresses from db");
        return emailAddressRepository.findAll();
    }
}
