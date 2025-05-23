package web.petbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.petbackend.entity.ApiResponse;
import web.petbackend.entity.HealthRecord;
import web.petbackend.service.HealthRecordService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/health")
public class HealthRecordController {
    
    @Autowired
    private HealthRecordService healthRecordService;
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    @PostMapping("/add")
    public ApiResponse<HealthRecord> addHealthRecord(@RequestBody HealthRecord healthRecord) {
        healthRecord.setRecordDate(LocalDateTime.now().format(FORMATTER));
        boolean success = healthRecordService.addHealthRecord(healthRecord);
        if (success) {
            return ApiResponse.success("添加健康记录成功", healthRecord);
        }
        return ApiResponse.error(500, "添加健康记录失败");
    }
    
    @PutMapping("/update")
    public ApiResponse<HealthRecord> updateHealthRecord(@RequestBody HealthRecord healthRecord) {
        healthRecord.setRecordDate(LocalDateTime.now().format(FORMATTER));
        boolean success = healthRecordService.updateHealthRecord(healthRecord);
        if (success) {
            return ApiResponse.success("更新健康记录成功", healthRecord);
        }
        return ApiResponse.error(500, "更新健康记录失败");
    }
    
    @DeleteMapping("/delete/{recordId}")
    public ApiResponse<String> deleteHealthRecord(@PathVariable Integer recordId) {
        boolean success = healthRecordService.deleteHealthRecord(recordId);
        if (success) {
            return ApiResponse.success("删除健康记录成功");
        }
        return ApiResponse.error(500, "删除健康记录失败");
    }
    
    @GetMapping("/list")
    public ApiResponse<List<HealthRecord>> getAllHealthRecords() {
        List<HealthRecord> records = healthRecordService.getAllHealthRecords();
        return ApiResponse.success("获取所有健康记录成功", records);
    }
    
    @GetMapping("/pet/{petId}")
    public ApiResponse<List<HealthRecord>> getHealthRecordsByPetId(@PathVariable Integer petId) {
        List<HealthRecord> records = healthRecordService.getHealthRecordsByPetId(petId);
        return ApiResponse.success("获取宠物健康记录成功", records);
    }
    
    @GetMapping("/{recordId}")
    public ApiResponse<HealthRecord> getHealthRecordById(@PathVariable Integer recordId) {
        HealthRecord record = healthRecordService.getHealthRecordById(recordId);
        if (record != null) {
            return ApiResponse.success("获取健康记录成功", record);
        }
        return ApiResponse.error(404, "健康记录不存在");
    }
} 