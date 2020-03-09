package com.example.unittesting.controller;

import com.example.unittesting.models.Pet;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.unittesting.service.PetService;

@RunWith(SpringRunner.class)
@WebMvcTest(PetController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @InjectMocks
    private PetController petController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    public void findAllPets() throws Exception {
        when(petService.getAllPet()).thenReturn(Arrays.asList(
                new Pet(100, "cat", "male", 2),
                new Pet(101, "dog", "female", 5)
        ));

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(100)))
                .andExpect(jsonPath("$[0].type", is("cat")))
                .andExpect(jsonPath("$[0].gender", is("male")))
                .andExpect(jsonPath("$[0].age", is(2)))
                .andExpect(jsonPath("$[1].id", is(101)))
                .andExpect(jsonPath("$[1].type", is("dog")))
                .andExpect(jsonPath("$[1].gender", is("female")))
                .andExpect(jsonPath("$[1].age", is(5)));
    }

    @Test
    public void findAllEmpty() throws Exception {
        when(petService.getAllPet()).thenReturn(Collections.emptyList());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void findPetById() throws Exception {
        when(petService.getPet(100)).thenReturn(new Pet(100, "cat", "male", 2));

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/pets/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(100)))
                .andExpect(jsonPath("$.type", is("cat")))
                .andExpect(jsonPath("$.gender", is("male")))
                .andExpect(jsonPath("$.age", is(2)));
    }

    @Test
    public void findEmptyById() throws Exception {
        when(petService.getPet(100)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/pets/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

}