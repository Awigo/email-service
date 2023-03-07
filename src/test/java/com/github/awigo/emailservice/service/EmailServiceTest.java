package com.github.awigo.emailservice.service;

import com.github.awigo.emailservice.model.Email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailServiceTest {

    private static final long ID = 1L;

    @Test
    @DisplayName("Get user by id test")
    void getByIdTest() {
        //given
        EmailService emailService = new EmailService();

        //when
        Email email = emailService.getById(ID);

        //then
        assertEquals(getEmail(), email);
    }

    private Email getEmail() {
        Email email = new Email();
        email.setId(ID);
        email.setAddress("bob@gmail.com");
        return email;
    }
}