package com.github.awigo.emailservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.awigo.emailservice.model.Email;
import com.github.awigo.emailservice.service.EmailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(EmailController.class)
class EmailControllerTest {

    private static final Long ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @Test
    @DisplayName("EmailController simple unit test")
    void controllerUnitTest() {
        //given
        when(emailService.getById(ID)).thenReturn(getEmail());
        EmailController controller = new EmailController(emailService);

        //when
        ResponseEntity<Email> result = controller.getById(ID);

        //then
        assertEquals(getEmail(), result.getBody());
    }

    @Test
    @DisplayName("EmailController get by id test")
    void getByIdTest() throws Exception {
        //given
        when(emailService.getById(ID)).thenReturn(getEmail());

        //when
        mockMvc.perform(get("/task/" + ID))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(getEmail().getAddress())))
                .andReturn();

        //then
        verify(emailService).getById(ID);
    }

    @Test
    @DisplayName("EmailController post test")
    void postTest() throws Exception {
        //given
        when(emailService.addEmail(getEmail())).thenReturn(ID);

        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/task")
                        .content(asJsonString(getEmail()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(String.valueOf(ID))))
                .andReturn();

        //then
        verify(emailService).addEmail(getEmail());
    }

    private String asJsonString(Email email) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(email);
    }

    private Email getEmail() {
        return new Email("jon@gmail.com");
    }
}