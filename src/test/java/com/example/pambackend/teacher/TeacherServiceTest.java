package com.example.pambackend.teacher;

import com.example.pambackend.response.TeacherLoginResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;

    @MockBean
    private TeacherRepository teacherRepository;

    @Test
    void addNewUser() {
        TeacherDTO teacherToLogin = new TeacherDTO();
        teacherToLogin.setUsername(null);
        teacherToLogin.setPassword(null);
        teacherService.addNewUser(teacherToLogin);
    }

    @Test
    void shouldSetResultValueAsTrueForValidTeacherLogin() {
        // GIVEN
        Teacher teacherToLogin = new Teacher();
        teacherToLogin.setUsername("ExistingUser");
        teacherToLogin.setPassword("Password");
        Mockito.when(teacherRepository.findByNameAndPassword("ExistingUser","Password")).thenReturn(Optional.of(teacherToLogin));

        // WHEN
        TeacherLoginResponse teacherLoginResponse = teacherService.findTeacher(teacherToLogin);
        boolean resultCheckInResponse = teacherLoginResponse.isResult();

        // THEN
        Assert.assertTrue(resultCheckInResponse);
    }

    @Test
    void shouldSetResultValueAsFalseForNotExistingTeacherLogin() {
        // GIVEN
        Teacher teacherToLogin = new Teacher();
        teacherToLogin.setUsername("NonExistingUser");
        teacherToLogin.setPassword("Password");
        Mockito.when(teacherRepository.findByNameAndPassword("NonExistingUser","Password")).thenReturn(Optional.empty());

        // WHEN
        TeacherLoginResponse teacherLoginResponse = teacherService.findTeacher(teacherToLogin);
        boolean resultCheckInResponse = teacherLoginResponse.isResult();

        // THEN
        Assert.assertFalse(resultCheckInResponse);
    }

    @Test
    void shouldSetResultValueAsFalseForEmptyValuesTeacherLogin() {
        // GIVEN
        Teacher teacherToLogin = new Teacher();
        teacherToLogin.setUsername("");
        teacherToLogin.setPassword("");
        Mockito.when(teacherRepository.findByNameAndPassword("","")).thenReturn(Optional.empty());

        // WHEN
        TeacherLoginResponse teacherLoginResponse = teacherService.findTeacher(teacherToLogin);
        boolean resultCheckInResponse = teacherLoginResponse.isResult();

        // THEN
        Assert.assertFalse(resultCheckInResponse);
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void saveMessage() {
    }

    @Test
    void getAllMessages() {
    }
}