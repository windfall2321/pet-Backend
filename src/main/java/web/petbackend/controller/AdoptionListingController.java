package web.petbackend.controller;

import web.petbackend.entity.AdoptionListing;
import web.petbackend.service.AdoptionListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adoption-listings")
public class AdoptionListingController {

    @Autowired
    private AdoptionListingService adoptionListingService;

    @PostMapping
    public String addAdoption(@RequestBody AdoptionListing listing) {
        adoptionListingService.addAdoption(listing);
        return "发布成功";
    }

    @GetMapping("/{id}")
    public AdoptionListing getAdoption(@PathVariable Integer id) {
        return adoptionListingService.getAdoptionById(id);
    }

    @GetMapping
    public List<AdoptionListing> getAllAdoptions() {
        return adoptionListingService.getAllAdoptions();
    }

    @PutMapping
    public String updateAdoption(@RequestBody AdoptionListing listing) {
        adoptionListingService.updateAdoption(listing);
        return "更新成功";
    }

    @DeleteMapping("/{id}")
    public String deleteAdoption(@PathVariable Integer id) {
        adoptionListingService.deleteAdoption(id);
        return "删除成功";
    }
}
