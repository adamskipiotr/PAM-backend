package com.example.pambackend.teacher;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TeacherControllerTest {

    @Autowired
    TeacherController teacherController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnIsOkResponseWhenRegisterRequestWithLoginAndPasswordSent() throws Exception {
        // GIVEN
        String user = "{\"username\": \"TeacherToAdd\", \"password\" : \"Password\"}";

        //WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/teacher/add")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnIsBadRequestResponseWhenRegisterRequestWithMissingPassword() throws Exception {
        // GIVEN
        String user = "{\"username\": \"ValidName\", \"password\" : \"\"}";

        // WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/teacher/add")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldReturnIsBadRequestResponseWhenRegisterRequestWithMissingLogin() throws Exception {
        // GIVEN
        String user = "{\"username\": \"\", \"password\" : \"Password\"}";

        // WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/teacher/add")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldReturnIsBadRequestResponseWhenRegisterRequestWithMissingLoginAndPassword() throws Exception {
        // GIVEN
        String user = "{\"username\": \"\", \"password\" : \"\"}";

        // WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/teacher/add")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}