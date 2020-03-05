package com.example.unittesting.controller;

import com.example.unittesting.models.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.unittesting.service.PetService;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PetController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @Test
    public void findAllPets() throws Exception {
        when(petService.getAllPet()).thenReturn(Arrays.asList(
                new Pet(100, "cat", "male", 2),
                new Pet(101, "dog", "male", 5),
                new Pet(102, "rabbit", "female", 3)
        ));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/pets"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().json("[{}, {}, {}]"));
    }

    @Test
    public void findAllEmpty() throws Exception {
        when(petService.getAllPet()).thenReturn(Collections.emptyList());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/pets"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().json("[]"));
    }

}