package com.example.unittesting.repository;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.unittesting.models.Pet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @Test
    public void testSavePet() {
        Pet pet1 = new Pet(1,"dog", "female", 5);
        petRepository.addPet(pet1);
        Pet pet2 = petRepository.getPet(pet1.getId());
        assertEquals(pet2.getId(), pet1.getId());
        assertEquals(pet2.getType(), pet1.getType());
        assertEquals(pet2.getGender(), pet1.getGender());
        assertEquals(pet2.getAge(), pet1.getAge());
    }

    @Test
    public void testFindAll() {
        Pet pet1 = new Pet(2,"rabbit", "female", 3);
        petRepository.addPet(pet1);
        List<Pet> pets = petRepository.getAllPet();
        assertThat(pets).hasSize(2);
    }

    @Test
    public void testFindById_NotFound() {
        Pet pet1 = petRepository.getPet(1000);
        assertNull(pet1);
    }

    @Test
    public void testUpdatePet() {
        Pet pet1 = new Pet(3,"cat", "male", 2);
        petRepository.addPet(pet1);
        Pet pet2 = petRepository.getPet(pet1.getId());
        assertEquals(pet2.getType(), pet1.getType());
        assertEquals(pet2.getGender(), pet1.getGender());
        assertEquals(pet2.getAge(), pet1.getAge());
        Pet pet3 = new Pet(pet2.getId(), "hamster", "female", 4);
        petRepository.updatePet(pet3.getId(), pet3);
        Pet pet4 = petRepository.getPet(pet3.getId());
        assertEquals(pet4.getType(), pet3.getType());
        assertEquals(pet4.getGender(), pet3.getGender());
        assertEquals(pet4.getAge(), pet3.getAge());

    }

    @Test
    public void testDeletePetById() {
        Pet pet1 = petRepository.getPet(2);
        assertNotNull(pet1);
        petRepository.deletePet(pet1.getId());
        Pet pet2 = petRepository.getPet(2);
        assertNull(pet2);
    }



}