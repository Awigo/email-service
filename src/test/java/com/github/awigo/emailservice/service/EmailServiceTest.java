package com.github.awigo.emailservice.service;

import com.github.awigo.emailservice.model.Email;
import com.github.awigo.emailservice.repository.EmailRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    private static final long ID = 1L;

    @Test
    @DisplayName("Get email by id test")
    void getByIdTest() {
        //given
        EmailRepository emailRepository = mock(EmailRepository.class);
        when(emailRepository.getReferenceById(ID)).thenReturn(getEmail());
        EmailService emailService = new EmailService(emailRepository);

        //when
        Email email = emailService.getById(ID);

        //then
        assertEquals(getEmail(), email);
        verify(emailRepository).getReferenceById(ID);
    }

    private Email getEmail() {
        Email email = new Email();
        email.setId(ID);
        email.setAddress("bob@gmail.com");
        return email;
    }
}