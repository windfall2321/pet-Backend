package web.petbackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import web.petbackend.entity.PetEncyclopedia;

import java.util.List;
@Mapper
public interface PetEncyMapper {
    List<PetEncyclopedia> findAllPetEncy();
}
