package web.petbackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import web.petbackend.entity.Pet;

import java.util.List;

@Mapper
public interface PetMapper {
    void insert(Pet pet);

    void update(Pet pet);

    Pet selectById(int PetId);

    List<Pet> selectAll();

    List<Pet> selectByOwnerId(int ownerId);
    void deleteByOwnerId(int ownerId);
    void deleteById(int PetId);
}
