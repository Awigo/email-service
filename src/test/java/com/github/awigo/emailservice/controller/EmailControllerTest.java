package com.github.awigo.emailservice.controller;

import com.github.awigo.emailservice.model.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmailControllerTest {

    @Test
    @DisplayName("Controller simple unit test")
    void controllerUnitTest() {
        //given
        Email email = getEmail();
        EmailController controller = new EmailController();

        //when
        Email result = controller.getById(1L);

        //then
        Assertions.assertEquals(email, result);

    }

    private Email getEmail() {
        Email email = new Email();
        email.setAddress("jon@gmail.com");
        return email;
    }
}