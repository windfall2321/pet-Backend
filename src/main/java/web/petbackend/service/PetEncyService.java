package web.petbackend.service;

import web.petbackend.entity.PageResult;
import web.petbackend.entity.PetEncyclopedia;

import java.util.List;

public interface PetEncyService {
    List<PetEncyclopedia> getAllPetEncy();
    PageResult<PetEncyclopedia> getPetEncyByPage(int pageNum, int pageSize);
    void addPetEncy(PetEncyclopedia petEncyclopedia);
    void deletePetEncy(Integer petEncyId);
    void updatePetEncy(PetEncyclopedia petEncyclopedia);

}