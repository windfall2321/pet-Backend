package web.petbackend.service;

import web.petbackend.entity.Pet;

import java.util.List;

public interface PetService {
    void addPet(Pet pet);
    void updatePet(Pet pet);
    void deletePet(Pet pet);
    List<Pet> getPets();
}
