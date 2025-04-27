package web.petbackend.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.petbackend.entity.PetEncyclopedia;
import web.petbackend.mapper.PetEncyMapper;
import web.petbackend.service.PetEncyService;

import java.util.List;

@RestController
@RequestMapping("/api/petEncy")
public class PetEncyController {
    @Resource
    private PetEncyService petEncyService;

    @GetMapping
    public List<PetEncyclopedia> findAllPetEncy() {
        return petEncyService.findAllPetEncy();
    }
}
