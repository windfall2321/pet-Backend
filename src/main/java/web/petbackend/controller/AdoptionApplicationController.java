package web.petbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.petbackend.entity.AdoptionApplication;
import web.petbackend.entity.ApiResponse;
import web.petbackend.service.AdoptionApplicationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
@CrossOrigin(origins = "http://localhost:3100", allowCredentials = "true")
@RestController
@RequestMapping("/api/adoption-applications")
public class AdoptionApplicationController {

    @Autowired
    private AdoptionApplicationService adoptionApplicationService;

    // 添加申请
    @PostMapping("/add")
    public ApiResponse<AdoptionApplication> addApplication(
            @RequestParam Integer adoptionId,
            @RequestParam Integer applicantId,
            @RequestParam(required = false) String reason
    ) {
        try {
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

    // 更新申请
    @PatchMapping("/update")
    public ApiResponse<AdoptionApplication> partialUpdateAdoptionApplication(
            @RequestParam(value = "adoptionApplicationId") Integer adoptionApplicationId,
            @RequestParam(value = "adoptionId", required = false) Integer adoptionId,
            @RequestParam(value = "applicantId", required = false) Integer applicantId,
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

            // 更新字段
            if (adoptionId != null) existing.setAdoptionId(adoptionId);
            if (applicantId != null) existing.setApplicantId(applicantId);
            if (reason != null) existing.setReason(reason);
            if (status != null) existing.setStatus(status);
            if (listedAt != null) existing.setListedAt(listedAt);
            if (adoptedAt != null) existing.setAdoptedAt(adoptedAt);
            if (reviewedBy != null) existing.setReviewedBy(reviewedBy);

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
