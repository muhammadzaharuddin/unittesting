package com.example.unittesting.repository;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.unittesting.models.Pet;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PetRepository petRepository;

//    @Before
//    public void setUp() {
//        Pet pet1 = new Pet(100, "cat", "male", 2);
//        Pet pet2 = new Pet(101, "dog", "male", 5);
//        Pet pet3 = new Pet(102, "rabbit", "female", 3);
//        petRepository.addPet(pet1);
//        petRepository.addPet(pet2);
//        petRepository.addPet(pet3);
//        testEntityManager.persist(pet1);
//        testEntityManager.persist(pet2);
//        testEntityManager.persist(pet3);
//    }

//    @After
//    public void tearDown() throws Exception {
//        petRepository.deletePet(100);
//        petRepository.deletePet(101);
//        petRepository.deletePet(102);
//    }

    @Test
    public void testSavePet() {
        Pet pet1 = new Pet(100, "cat", "male", 2);
        petRepository.addPet(pet1);
        Pet pet2 = petRepository.getPet(100);
        assertNotNull(pet1);
        assertEquals(pet2.getId(), pet1.getId());
        assertEquals(pet2.getType(), pet1.getType());
        assertEquals(pet2.getGender(), pet1.getGender());
        assertEquals(pet2.getAge(), pet1.getAge());
    }

    @Test
    public void testFindAll() {
        List<Pet> pets = petRepository.getAllPet();
//        assertEquals(3, pets.size());
        assertThat(pets).hasSize(3);
    }

    @Test
    public void testFindById() {
        Pet pet1 = new Pet(100, "cat", "male", 2);
        testEntityManager.persist(pet1);
        Pet optPet = petRepository.getPet(100);
        assertThat(optPet).isNotNull();
    }
//
//    @Test
//    public void testFindById_Basic() {
//        Pet pet1 = new Pet(100, "cat", "male", 2);
//        petRepository.addPet(pet1);
//        Pet optPet = petRepository.getPet(100);
//        assertNotNull(optPet);
//    }
//
//    @Test
//    public void testFindById_NotFound() {
//        Pet optPet = petRepository.getPet(1000);
//        assertNull(optPet);
//    }

}