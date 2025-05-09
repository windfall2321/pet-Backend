package web.petbackend.service;

import web.petbackend.entity.Pet;

import java.util.List;

public interface PetService {
    Pet addPet(Pet pet);
    Pet updatePet(Pet pet);
    void deletePet(Pet pet);
    List<Pet> getPets();

    List<Pet> selectByOwnerId(int ownerId);
    void deleteByOwnerId(int ownerId);
}
