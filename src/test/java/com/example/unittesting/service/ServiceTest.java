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
            new Pet(101, "dog", "female", 5)
        ));

        List<Pet> allPets = petService.getAllPet();
        assertEquals(100, allPets.get(0).getId());
        assertEquals("cat", allPets.get(0).getType());
        assertEquals("male", allPets.get(0).getGender());
        assertEquals(2, allPets.get(0).getAge());
        assertEquals(101, allPets.get(1).getId());
        assertEquals("dog", allPets.get(1).getType());
        assertEquals("female", allPets.get(1).getGender());
        assertEquals(5, allPets.get(1).getAge());
    }

    @Test
    public void findEmptyPet() {
        Mockito.when(petRepository.getAllPet()).thenReturn(Collections.emptyList());
        List<Pet> allPets = petService.getAllPet();
        assertEquals(0, allPets.size());
    }

    @Test
    public void findPetById() {
        Mockito.when(petRepository.getPet(100)).thenReturn(new Pet(100, "cat", "male", 2));

        Pet pet1 = petService.getPet(100);
        assertEquals(100, pet1.getId());
        assertEquals("cat", pet1.getType());
        assertEquals("male", pet1.getGender());
        assertEquals(2, pet1.getAge());
    }

    @Test
    public void findEmptyPetById() {
        Mockito.when(petRepository.getPet(100)).thenReturn(null);
        Pet pet1 = petService.getPet(100);
        assertEquals(null, pet1);
    }

}