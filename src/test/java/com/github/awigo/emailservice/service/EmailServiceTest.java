package com.github.awigo.emailservice.service;

import com.github.awigo.emailservice.model.EmailAddress;
import com.github.awigo.emailservice.repository.EmailAddressRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EmailServiceTest {

    private static final long ID = 42L;

    @Autowired
    private EmailService emailService;

    @MockBean
    private EmailAddressRepository emailRepository;

    @Test
    @DisplayName("Get email by id test")
    void getByIdTest() {
        //given
        when(emailRepository.findById(ID)).thenReturn(Optional.of(getEmail()));

        //when
        EmailAddress email = emailService.getById(ID);

        //then
        assertEquals(getEmail(), email);
        verify(emailRepository).findById(ID);
    }

    @Test
    @DisplayName("Post email test")
    void postEmailTest() {
        //given
        EmailAddress email = getEmail();
        when(emailRepository.save(email)).thenReturn(email);

        //when
        Long emailId = emailService.addEmailAddress(email);

        //then
        assertEquals(email.getId(), emailId);
        verify(emailRepository).save(email);
    }

    @Test
    @DisplayName("Put email test")
    void putEmailTest() {
        //given
        EmailAddress beforeUpdate = getEmail();
        when(emailRepository.findById(ID)).thenReturn(Optional.of(beforeUpdate));
        when(emailRepository.save(beforeUpdate)).thenReturn(beforeUpdate);

        EmailAddress afterUpdate = new EmailAddress();
        afterUpdate.setId(ID);
        afterUpdate.setAddress("updated@gmail.com");

        //when
        EmailAddress updated = emailService.updateById(ID, afterUpdate);

        //then
        assertEquals(afterUpdate.getId(), updated.getId());
        assertEquals(afterUpdate.getAddress(), updated.getAddress());
        verify(emailRepository).findById(ID);
        verify(emailRepository).save(afterUpdate);
    }

    @Test
    @DisplayName("Delete email test")
    void deleteEmailTest() {
        //given
        EmailAddress email = getEmail();
        when(emailRepository.findById(email.getId())).thenReturn(Optional.of(email));

        //when
        EmailAddress deleted = emailService.deleteById(ID);

        //then
        assertEquals(email, deleted);
        verify(emailRepository).delete(email);
    }

    private EmailAddress getEmail() {
        EmailAddress email = new EmailAddress();
        email.setId(ID);
        email.setAddress("bob@gmail.com");
        return email;
    }
}