package web.petbackend.controller;

import web.petbackend.entity.AdoptionListing;
import web.petbackend.entity.ApiResponse;
import web.petbackend.service.AdoptionListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
            @RequestParam(value = "description", required = false) String description
    ) {
        // 创建 AdoptionListing 对象
        AdoptionListing listing = new AdoptionListing();
        listing.setPetId(petId);
        listing.setListedBy(listedBy);
        listing.setDescription(Objects.requireNonNullElse(description, "无"));
        listing.setStatus("available");
        listing.setListedAt(LocalDateTime.now());

        // 调用服务层处理
        adoptionListingService.addAdoption(listing);
        return ApiResponse.success("发布成功", listing);
    }


    // 根据id查询领养信息或查询所有领养信息
    @GetMapping("/get")
    public ApiResponse<?> getAdoption(@RequestParam(value = "id", required = false) Integer id) {
        if (id != null) {
            // 如果提供了 id，则返回该 id 的领养信息
            AdoptionListing adoptionListing = adoptionListingService.getAdoptionById(id);
            return ApiResponse.success("查询成功", adoptionListing);
        } else {
            // 如果没有提供 id，则返回所有领养信息
            List<AdoptionListing> adoptionListings = adoptionListingService.getAllAdoptions();
            return ApiResponse.success("查询全部成功", adoptionListings);
        }
    }

    // 部分更新领养信息
    @PatchMapping("/update")
    public ApiResponse<AdoptionListing> partialUpdateAdoption(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "petId", required = false) Integer petId,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "adoptedAt", required = false) LocalDateTime adoptedAt) {

        AdoptionListing existingListing = adoptionListingService.getAdoptionById(id);
        if (petId != null) {
            existingListing.setPetId(petId);
        }
        if (description != null) {
            existingListing.setDescription(description);
        }
        if (status != null) {
            existingListing.setStatus(status);
        }
        if (adoptedAt != null) {
            existingListing.setAdoptedAt(adoptedAt);
        }

        // 保存更新后的记录
        adoptionListingService.updateAdoption(existingListing);

        return ApiResponse.success("更新成功", existingListing);
    }


    // 删除领养信息
    @DeleteMapping("/delete")
    public ApiResponse<String> deleteAdoption(@RequestParam Integer id) {
        adoptionListingService.deleteAdoption(id);
        return ApiResponse.success("删除成功");
    }
}
