package web.petbackend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import web.petbackend.entity.PetEncyclopedia;

import java.util.List;
@Mapper
public interface PetEncyMapper {
    List<PetEncyclopedia> getAllPetEncy();

    void addPetEncy(PetEncyclopedia petEncyclopedia);
    void deletePetEncy(int id);

    void updatePetEncy(PetEncyclopedia petEncyclopedia);

    List<PetEncyclopedia> getPetEncyByPage(@Param("offset") int offset, @Param("size") int size);

    Integer countItems(); // 统计总条数
}
