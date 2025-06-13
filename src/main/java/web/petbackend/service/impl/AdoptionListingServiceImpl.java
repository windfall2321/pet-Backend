package web.petbackend.service.impl;

import org.springframework.beans.BeanUtils;
import web.petbackend.dto.AdoptionListingDTO;
import web.petbackend.entity.AdoptionListing;
import web.petbackend.entity.Pet;
import web.petbackend.mapper.AdoptionListingMapper;
import web.petbackend.service.AdoptionListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.petbackend.service.PetService;

import java.util.List;

@Service
public class AdoptionListingServiceImpl implements AdoptionListingService {

    @Autowired
    private AdoptionListingMapper adoptionListingMapper;
    @Autowired
    private PetService petService;
    @Override
    public int addAdoption(AdoptionListing listing) {
        return adoptionListingMapper.insert(listing);
    }

    @Override
    public AdoptionListing getAdoptionById(Integer adoptionId) {
        return adoptionListingMapper.selectById(adoptionId);
    }
    @Override
    public List<AdoptionListing> getAdoptionByUserId(Integer userId) {
        return adoptionListingMapper.selectByUserId(userId);
    }
    @Override
    public List<AdoptionListing> getAllAdoptions() {
        return adoptionListingMapper.selectAll();
    }

    @Override
    public int updateAdoption(AdoptionListing listing) {
        return adoptionListingMapper.update(listing);
    }

    @Override
    public int deleteAdoption(Integer adoptionId) {
        return adoptionListingMapper.deleteById(adoptionId);
    }

    @Override
    public AdoptionListingDTO convertToDTO(AdoptionListing listing) {
        if (listing == null) return null;

        AdoptionListingDTO dto = new AdoptionListingDTO();
        BeanUtils.copyProperties(listing, dto);

        // 获取宠物信息并赋值需要的字段
        Pet pet = petService.selectById(listing.getPetId());
        if (pet != null) {
            dto.setPetName(pet.getName());
            dto.setPetBreed(pet.getBreed());
            dto.setPetGender(pet.getGender());
            dto.setPetCity(pet.getCity());
            // 使用领养信息中的图片，如果没有则使用宠物的图片
            dto.setPetImage(listing.getImage() != null ? listing.getImage() : pet.getImage());
        }

        return dto;
    }

    @Override
    public List<AdoptionListing> searchByPetName(String petName) {
        return adoptionListingMapper.searchByPetName(petName);
    }

}

