package com.github.awigo.emailservice.service;

import com.github.awigo.emailservice.model.Email;
import com.github.awigo.emailservice.repository.EmailRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    private static final long ID = 42L;

    @Test
    @DisplayName("Get email by id test")
    void getByIdTest() {
        //given
        EmailRepository emailRepository = mock(EmailRepository.class);
        when(emailRepository.findById(ID)).thenReturn(Optional.of(getEmail()));
        EmailService emailService = new EmailService(emailRepository);

        //when
        Email email = emailService.getById(ID);

        //then
        assertEquals(getEmail(), email);
        verify(emailRepository).findById(ID);
    }

    @Test
    @DisplayName("Post email test")
    void postEmailTest() {
        //given
        Email email = getEmail();
        EmailRepository emailRepository = mock(EmailRepository.class);
        when(emailRepository.save(email)).thenReturn(email);
        EmailService emailService = new EmailService(emailRepository);

        //when
        Long emailId = emailService.addEmail(email);

        //then
        assertEquals(email.getId(), emailId);
        verify(emailRepository).save(email);
    }

    private Email getEmail() {
        Email email = new Email();
        email.setId(ID);
        email.setAddress("bob@gmail.com");
        return email;
    }
}