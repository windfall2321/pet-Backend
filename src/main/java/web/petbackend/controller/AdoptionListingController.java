package web.petbackend.controller;

import org.springframework.format.annotation.DateTimeFormat;
import web.petbackend.dto.AdoptionListingDTO;
import web.petbackend.entity.AdoptionListing;
import web.petbackend.entity.ApiResponse;
import web.petbackend.service.AdoptionListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3100", allowCredentials = "true")
@RestController
@RequestMapping("/api/adoption-listings")
public class AdoptionListingController {

    @Autowired
    private AdoptionListingService adoptionListingService;

    // 发布领养信息
    @PostMapping("/add")
    public ApiResponse<AdoptionListing> addAdoption(
            @RequestParam Integer petId,
            @RequestParam Integer listedBy,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String image
    ) {
        try {
            AdoptionListing listing = new AdoptionListing();
            listing.setPetId(petId);
            listing.setListedBy(listedBy);
            listing.setDescription(Objects.requireNonNullElse(description, "无"));
            listing.setStatus("available");
            listing.setListedAt(LocalDateTime.now());
            if (image != null) listing.setImage(image);

            adoptionListingService.addAdoption(listing);
            return ApiResponse.success("发布成功", listing);
        } catch (Exception e) {
            return ApiResponse.error(500, "发布领养信息失败: " + e.getMessage());
        }
    }

    // 根据id查询领养信息或查询所有领养信息
    @GetMapping("/get")
    public ApiResponse<?> getAdoption(@RequestParam(value = "id", required = false) Integer id) {
        try {
            if (id != null) {
                AdoptionListing adoptionListing = adoptionListingService.getAdoptionById(id);
                if (adoptionListing == null) {
                    return ApiResponse.error(404, "未找到对应的领养信息");
                }
                AdoptionListingDTO dto = adoptionListingService.convertToDTO(adoptionListing);

                return ApiResponse.success("查询成功", dto);
            } else {
                List<AdoptionListing> adoptionListings = adoptionListingService.getAllAdoptions();
                List<AdoptionListingDTO> dtoList = adoptionListings.stream()
                        .map(adoptionListingService::convertToDTO)
                        .collect(Collectors.toList());
                return ApiResponse.success("查询全部成功", dtoList);
            }
        } catch (Exception e) {
            return ApiResponse.error(500, "查询失败: " + e.getMessage());
        }
    }

    // 部分更新领养信息
    @PatchMapping("/update")
    public ApiResponse<AdoptionListing> updateAdoption(
            @RequestParam Integer adoptionId,
            @RequestParam(required = false) Integer petId,
            @RequestParam(required = false) Integer listedBy,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String image,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime listedAt,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime adoptedAt
    ) {
        try {
            AdoptionListing listing = adoptionListingService.getAdoptionById(adoptionId);
            if (listing == null) {
                return ApiResponse.error(404, "未找到该领养信息");
            }

            if (petId != null) listing.setPetId(petId);
            if (listedBy != null) listing.setListedBy(listedBy);
            if (description != null) listing.setDescription(description);
            if (status != null) listing.setStatus(status);
            if (image != null) listing.setImage(image);
            if (listedAt != null) listing.setListedAt(listedAt);
            if (adoptedAt != null) listing.setAdoptedAt(adoptedAt);

            adoptionListingService.updateAdoption(listing);
            return ApiResponse.success("更新成功", listing);
        } catch (Exception e) {
            return ApiResponse.error(500, "更新失败: " + e.getMessage());
        }
    }

    // 删除领养信息
    @DeleteMapping("/delete")
    public ApiResponse<String> deleteAdoption(@RequestParam Integer id) {
        try {
            AdoptionListing listing = adoptionListingService.getAdoptionById(id);
            if (listing == null) {
                return ApiResponse.error(404, "未找到该领养信息，无法删除");
            }
            adoptionListingService.deleteAdoption(id);
            return ApiResponse.success("删除成功");
        } catch (Exception e) {
            return ApiResponse.error(500, "删除失败: " + e.getMessage());
        }
    }
}
