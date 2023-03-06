package com.github.awigo.emailservice.controller;

import com.github.awigo.emailservice.model.Email;
import com.github.awigo.emailservice.service.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(EmailController.class)
class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @Test
    @DisplayName("Controller simple unit test")
    void controllerUnitTest() {
        //given
        Email email = getEmail();
        EmailService emailService = Mockito.mock(EmailService.class);
        when(emailService.getById(1L)).thenReturn(email);
        EmailController controller = new EmailController(emailService);

        //when
        Email result = controller.getById(1L);

        //then
        Assertions.assertEquals(email, result);
    }

    @Test
    @DisplayName("Controller Mock MVC test")
    void controllerMockMvcTest() throws Exception {
        //given
        Email email = getEmail();
        when(emailService.getById(1L)).thenReturn(email);

        //when
        MvcResult mvcResult = mockMvc.perform(get("/task/1"))
                .andExpect(status().isOk())
                .andReturn();

        //then
        verify(emailService).getById(1L);
    }

    private Email getEmail() {
        return new Email("jon@gmail.com");
    }
}