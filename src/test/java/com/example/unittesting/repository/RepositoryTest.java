package com.example.unittesting.repository;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;

import com.example.unittesting.models.Pet;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @Before
    public void setUp() throws Exception {
        Pet pet1 = new Pet(100, "cat", "male", 2);
        Pet pet2 = new Pet(101, "dog", "male", 5);
        Pet pet3 = new Pet(102, "rabbit", "female", 3);
        petRepository.addPet(pet1);
        petRepository.addPet(pet2);
        petRepository.addPet(pet3);
    }

    @After
    public void tearDown() throws Exception {
        petRepository.deletePet(100);
        petRepository.deletePet(101);
        petRepository.deletePet(102);
    }

    @Test
    public void testFindAll() {
        List<Pet> all = petRepository.getAllPet();
        assertEquals(3, all.size());
    }

    @Test
    public void testFindById_Basic() {
        Pet optPet = petRepository.getPet(100);
        assertNotNull(optPet);
    }

    @Test
    public void testFindById_NotFound() {
        Pet optPet = petRepository.getPet(1000);
        assertNull(optPet);
    }

}