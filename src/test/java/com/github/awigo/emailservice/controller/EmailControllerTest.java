package com.github.awigo.emailservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.awigo.emailservice.model.EmailAddress;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
        ResponseEntity<EmailAddress> result = controller.getById(ID);

        //then
        assertEquals(getEmail(), result.getBody());
    }

    @Test
    @DisplayName("EmailController get by id test")
    void getByIdTest() throws Exception {
        //given
        when(emailService.getById(ID)).thenReturn(getEmail());

        //when
        mockMvc.perform(get("/email/" + ID))
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
        when(emailService.addEmailAddress(getEmail())).thenReturn(ID);

        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/email")
                        .content(asJsonString(getEmail()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString(String.valueOf(ID))))
                .andReturn();

        //then
        verify(emailService).addEmailAddress(getEmail());
    }

    @Test
    @DisplayName("EmailController put test")
    void putTest() throws Exception {
        //given
        when(emailService.updateById(ID, getEmail())).thenReturn(getEmail());

        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/email/" + ID)
                        .content(asJsonString(getEmail()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(getEmail().getAddress())))
                .andReturn();

        //then
        verify(emailService).updateById(ID, getEmail());
    }

    @Test
    @DisplayName("EmailController delete test")
    void deleteTest() throws Exception {
        //given
        when(emailService.deleteById(ID)).thenReturn(getEmail());

        //when
        mockMvc.perform(delete("/email/" + ID))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(getEmail().getAddress())))
                .andReturn();

        //then
        verify(emailService).deleteById(ID);
    }

    private String asJsonString(EmailAddress email) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(email);
    }

    private EmailAddress getEmail() {
        return new EmailAddress("jon@gmail.com");
    }
}