package web.petbackend.service.impl;

import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.petbackend.entity.PageResult;
import web.petbackend.entity.PetEncyclopedia;
import web.petbackend.mapper.PetEncyMapper;
import web.petbackend.service.PetEncyService;

import java.util.List;
@Service
public class PetEncyServiceImpl implements PetEncyService {
    @Autowired
    private PetEncyMapper petEncyMapper;

    @Override
    public List<PetEncyclopedia> getAllPetEncy() {
        return petEncyMapper.getAllPetEncy();
    }

    @Override
    public void addPetEncy(PetEncyclopedia petEncyclopedia) {
        petEncyMapper.addPetEncy(petEncyclopedia);
    }

    @Override
    public void deletePetEncy(Integer petEncyId) {
        petEncyMapper.deletePetEncy(petEncyId);
    }

    @Override
    public void updatePetEncy(PetEncyclopedia petEncyclopedia) {
        petEncyMapper.updatePetEncy(petEncyclopedia);
    }
    @Override
    public PageResult<PetEncyclopedia> getPetEncyByPage(int page, int size) {
        int offset = (page - 1) * size;
        List<PetEncyclopedia> petencys = petEncyMapper.getPetEncyByPage(offset, size);
        long total = petEncyMapper.countItems();
        return new PageResult<>(total, petencys);
    }
}
