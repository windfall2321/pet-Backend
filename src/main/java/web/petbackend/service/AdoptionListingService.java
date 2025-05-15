package web.petbackend.service;

import web.petbackend.dto.AdoptionListingDTO;
import web.petbackend.entity.AdoptionListing;

import java.util.List;

public interface AdoptionListingService {
    int addAdoption(AdoptionListing listing);

    AdoptionListing getAdoptionById(Integer adoptionId);
    List<AdoptionListing> getAdoptionByUserId(Integer userId);

    List<AdoptionListing> getAllAdoptions();

    int updateAdoption(AdoptionListing listing);

    int deleteAdoption(Integer adoptionId);

    AdoptionListingDTO convertToDTO(AdoptionListing listing);

}
