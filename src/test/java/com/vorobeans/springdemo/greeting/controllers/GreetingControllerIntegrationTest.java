package com.vorobeans.springdemo.greeting.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {GreetingController.class})
@WebMvcTest
class GreetingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Greeting should return the default greeting if no name is provided.")
    public void greeting_defaultGreeting () throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/greeting")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Hello, World!"));
    }

    @Test
    @DisplayName("Greeting should return the greeting for a user when their name is provided.")
    public void greeting_greetByName () throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/greeting").param("name", "Beans")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").value("Hello, Beans!"));
    }
}