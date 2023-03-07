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

    @Test
    @DisplayName("Put email test")
    void putEmailTest() {
        //given
        Email beforeUpdate = getEmail();
        EmailRepository emailRepository = mock(EmailRepository.class);
        when(emailRepository.findById(ID)).thenReturn(Optional.of(beforeUpdate));
        when(emailRepository.save(beforeUpdate)).thenReturn(beforeUpdate);

        EmailService emailService = new EmailService(emailRepository);

        Email afterUpdate = new Email();
        afterUpdate.setId(ID);
        afterUpdate.setAddress("updated@gmail.com");

        //when
        Email updated = emailService.updateById(ID, afterUpdate);

        //then
        assertEquals(afterUpdate.getId(), updated.getId());
        assertEquals(afterUpdate.getAddress(), updated.getAddress());
        verify(emailRepository).findById(ID);
        verify(emailRepository).save(afterUpdate);
    }

    private Email getEmail() {
        Email email = new Email();
        email.setId(ID);
        email.setAddress("bob@gmail.com");
        return email;
    }
}