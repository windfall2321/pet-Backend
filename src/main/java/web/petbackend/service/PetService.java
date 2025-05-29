package web.petbackend.service;

import org.springframework.web.multipart.MultipartFile;
import web.petbackend.entity.Pet;

import java.util.List;

public interface PetService {
    Pet addPet(Pet pet);
    Pet updatePet(Pet pet);
    void deletePet(Pet pet);
    List<Pet> getPets();
    Pet selectById(int petId);
    Pet uploadImage(Integer petId, MultipartFile file);

    void deleteImage(Integer petId);
    List<Pet> selectByOwnerId(int ownerId);
    void deleteByOwnerId(int ownerId);
}
