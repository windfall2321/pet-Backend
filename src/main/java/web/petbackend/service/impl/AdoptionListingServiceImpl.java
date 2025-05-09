package web.petbackend.service.impl;

import web.petbackend.entity.AdoptionListing;
import web.petbackend.mapper.AdoptionListingMapper;
import web.petbackend.service.AdoptionListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionListingServiceImpl implements AdoptionListingService {

    @Autowired
    private AdoptionListingMapper adoptionListingMapper;

    @Override
    public int addAdoption(AdoptionListing listing) {
        return adoptionListingMapper.insert(listing);
    }

    @Override
    public AdoptionListing getAdoptionById(Integer adoptionId) {
        return adoptionListingMapper.selectById(adoptionId);
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
}
