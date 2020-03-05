package com.example.unittesting.service;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.unittesting.repository.PetRepository;
import com.example.unittesting.models.Pet;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

    @InjectMocks
    private PetService petService;

    @Mock
    private PetRepository petRepository;

    @Test
    public void findAllPet() {
        Mockito.when(petRepository.getAllPet()).thenReturn(Arrays.asList(
            new Pet(100, "cat", "male", 2),
            new Pet(101, "dog", "male", 5),
            new Pet(102, "rabbit", "female", 3)
        ));

        List<Pet> allPets = petService.getAllPet();
        assertEquals("cat", allPets.get(0).getType());
        assertEquals("dog", allPets.get(1).getType());
        assertEquals("rabbit", allPets.get(2).getType());
    }

}