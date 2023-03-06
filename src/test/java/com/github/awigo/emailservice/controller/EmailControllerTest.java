package com.github.awigo.emailservice.controller;

import com.github.awigo.emailservice.model.Email;
import com.github.awigo.emailservice.service.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EmailControllerTest {

    @Test
    @DisplayName("Controller simple unit test")
    void controllerUnitTest() {
        //given
        Email email = getEmail();
        EmailService emailService = Mockito.mock(EmailService.class);
        Mockito.when(emailService.getById(1L)).thenReturn(email);
        EmailController controller = new EmailController(emailService);

        //when
        Email result = controller.getById(1L);

        //then
        Assertions.assertEquals(email, result);
    }

    private Email getEmail() {
        return new Email("jon@gmail.com");
    }
}