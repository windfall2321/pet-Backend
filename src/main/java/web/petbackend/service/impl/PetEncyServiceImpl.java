package web.petbackend.service.impl;

import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import web.petbackend.entity.PetEncyclopedia;
import web.petbackend.mapper.PetEncyMapper;
import web.petbackend.service.PetEncyService;

import java.util.List;
@Service
public class PetEncyServiceImpl implements PetEncyService {
    @Resource
    private PetEncyMapper petEncyMapper;
    @Override
    public List<PetEncyclopedia> findAllPetEncy() {
        return petEncyMapper.findAllPetEncy();
    }
}
