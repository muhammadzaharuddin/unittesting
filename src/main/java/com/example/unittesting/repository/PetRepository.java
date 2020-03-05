package com.example.unittesting.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

import com.example.unittesting.models.*;

@Repository
public class PetRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addPet(Pet pet) {
        jdbcTemplate.update("INSERT INTO test.pet VALUES (?, ?, ?, ?)", pet.getId(), pet.getType(), pet.getGender(), pet.getAge());
    }

    public Pet getPet(Integer petId) {
        Pet pet;
        String sql = "SELECT * FROM test.pet WHERE id = ?";
        try{
            pet = jdbcTemplate.queryForObject(sql, new Object[]{petId}, (rs, rowNum) ->
                    new Pet(
                            rs.getInt("id"),
                            rs.getString("type"),
                            rs.getString("gender"),
                            rs.getInt("age")
                    )
            );
            return pet;
        } catch (Exception e){
            return null;
        }
    }

    public List<Pet> getAllPet() {
        try{
            return jdbcTemplate.query("SELECT * FROM test.pet", (rs, rowNum) ->
                    new Pet(
                            rs.getInt("id"),
                            rs.getString("type"),
                            rs.getString("gender"),
                            rs.getInt("age")
                    ));
        } catch (Exception e){
            return null;
        }
    }

    public void updatePet(Integer petId, Pet newPet) {
        jdbcTemplate.update("UPDATE test.pet SET id = ?, type = ?, gender = ?, age = ? WHERE id = ?", newPet.getId(), newPet.getType(), newPet.getGender(), newPet.getAge(), petId);
    }

    public void deletePet(Integer petId) {
        jdbcTemplate.update("DELETE FROM test.pet WHERE id = ?", petId);
    }

}