package web.petbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.petbackend.entity.AdoptionApplication;
import web.petbackend.entity.AdoptionListing;
import web.petbackend.entity.ApiResponse;
import web.petbackend.service.AdoptionApplicationService;
import web.petbackend.service.AdoptionListingService;
import web.petbackend.utils.UserContextHolder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
@CrossOrigin(origins = "http://localhost:3100", allowCredentials = "true")
@RestController
@RequestMapping("/api/adoption-applications")
public class AdoptionApplicationController {

    @Autowired
    private AdoptionApplicationService adoptionApplicationService;

    @Autowired
    private AdoptionListingService adoptionListingService;

    // 添加申请
    @PostMapping("/add")
    public ApiResponse<AdoptionApplication> addApplication(
            @RequestParam Integer adoptionId,
            @RequestParam(required = false) String reason
    ) {
        try {
            Integer applicantId = UserContextHolder.getUserId();
            AdoptionApplication application = new AdoptionApplication();
            application.setAdoptionId(adoptionId);
            application.setApplicantId(applicantId);
            application.setReason(Objects.requireNonNullElse(reason, "无"));
            application.setStatus("pending");
            application.setListedAt(LocalDateTime.now());

            adoptionApplicationService.addApplication(application);
            return ApiResponse.success("申请成功", application);
        } catch (IllegalArgumentException e) {
            return ApiResponse.error(400, "参数非法: " + e.getMessage());
        } catch (Exception e) {
            return ApiResponse.error(500, "添加申请失败: " + e.getMessage());
        }
    }

    // 查询申请
    @GetMapping("/get")
    public ApiResponse<?> getApplication(@RequestParam(required = false) Integer id) {
        try {
            if (id != null) {
                AdoptionApplication app = adoptionApplicationService.getApplicationById(id);
                if (app == null) {
                    return ApiResponse.error(404, "未找到该申请");
                }
                return ApiResponse.success("查询成功", app);
            } else {
                List<AdoptionApplication> apps = adoptionApplicationService.getAllApplications();
                return ApiResponse.success("查询全部成功", apps);
            }
        } catch (Exception e) {
            return ApiResponse.error(500, "查询申请失败: " + e.getMessage());
        }
    }
    @GetMapping("/getbyuserid")
    public ApiResponse<?> getApplicationByUserId() {
        try {
            Integer userId = UserContextHolder.getUserId();
            List<AdoptionApplication> apps = adoptionApplicationService.getApplicationsByUserId(userId);
                if (apps == null) {
                    return ApiResponse.error(404, "未找到该申请");
                }
                return ApiResponse.success("查询成功", apps);

        } catch (Exception e) {
            return ApiResponse.error(500, "查询申请失败: " + e.getMessage());
        }
    }@GetMapping("/getbyadoptionid")
    public ApiResponse<?> getApplicationByAdoptionId(@RequestParam(required = true) Integer id) {
        try {
                List<AdoptionApplication> apps = adoptionApplicationService.getApplicationsByAdoptionId(id);
                if (apps == null) {
                    return ApiResponse.error(404, "未找到该申请");
                }
                return ApiResponse.success("查询成功", apps);

        } catch (Exception e) {
            return ApiResponse.error(500, "查询申请失败: " + e.getMessage());
        }
    }

    // 更新申请
    @PatchMapping("/update")
    public ApiResponse<AdoptionApplication> partialUpdateAdoptionApplication(
            @RequestParam(value = "adoptionApplicationId") Integer adoptionApplicationId,
            @RequestParam(value = "adoptionId", required = false) Integer adoptionId,
            @RequestParam(value = "reason", required = false) String reason,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "listedAt", required = false) LocalDateTime listedAt,
            @RequestParam(value = "adoptedAt", required = false) LocalDateTime adoptedAt,
            @RequestParam(value = "reviewedBy", required = false) Integer reviewedBy) {

        try {
            AdoptionApplication existing = adoptionApplicationService.getApplicationById(adoptionApplicationId);
            if (existing == null) {
                return ApiResponse.error(404, "未找到对应的申请记录");
            }
            Integer userId = UserContextHolder.getUserId();
            if(userId == null) return ApiResponse.error(500, "用户未登录");

            // 更新申请字段
            if (adoptionId != null) existing.setAdoptionId(adoptionId);
            if (reason != null) existing.setReason(reason);
            if (status != null) existing.setStatus(status);
            if (listedAt != null) existing.setListedAt(listedAt);
            if (adoptedAt != null) existing.setAdoptedAt(adoptedAt);
            if (reviewedBy != null) existing.setReviewedBy(reviewedBy);

            // 如果状态更新为已同意，同时更新领养信息
            if ("approved".equals(status)) {
                LocalDateTime now = LocalDateTime.now();
                existing.setAdoptedAt(now);

                // 更新领养信息
                AdoptionListing listing = adoptionListingService.getAdoptionById(existing.getAdoptionId());
                if (listing != null) {
                    listing.setStatus("adopted");
                    listing.setAdoptedAt(now);
                    adoptionListingService.updateAdoption(listing);
                }
            }

            adoptionApplicationService.updateApplication(existing);
            return ApiResponse.success("更新成功", existing);

        } catch (Exception e) {
            return ApiResponse.error(500, "更新申请失败: " + e.getMessage());
        }
    }

    // 删除申请
    @DeleteMapping("/delete")
    public ApiResponse<String> deleteApplication(@RequestParam Integer id) {
        try {
            AdoptionApplication app = adoptionApplicationService.getApplicationById(id);
            if (app == null) {
                return ApiResponse.error(404, "该申请不存在");
            }
            adoptionApplicationService.deleteApplication(id);
            return ApiResponse.success("删除成功");
        } catch (Exception e) {
            return ApiResponse.error(500, "删除失败: " + e.getMessage());
        }
    }
}
