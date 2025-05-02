package web.petbackend.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import web.petbackend.entity.Pet;
import web.petbackend.mapper.PetMapper;
import web.petbackend.mapper.UserMapper;
import web.petbackend.service.PetService;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Resource
    private PetMapper petMapper;

    @Override
    public Pet addPet(Pet pet) {
    petMapper.insert(pet);
    return pet;
    }

    @Override
    public Pet updatePet(Pet pet) {
    petMapper.update(pet);
    return pet;
    }

    @Override
    public void deletePet(Pet pet) {
        petMapper.deleteById(pet.getPetId());
    }

    @Override
    public List<Pet> getPets() {
        return petMapper.selectAll();

    }

    @Override
    public List<Pet> selectByOwnerId(int ownerId) {
        return petMapper.selectByOwnerId(ownerId);
    }

    @Override
    public void deleteByOwnerId(int ownerId) {
        petMapper.deleteByOwnerId(ownerId);

    }
}
