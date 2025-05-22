package web.petbackend.service;

import web.petbackend.entity.HealthRecord;
import java.util.List;

public interface HealthRecordService {
    // 添加健康记录
    boolean addHealthRecord(HealthRecord healthRecord);
    
    // 删除健康记录
    boolean deleteHealthRecord(Integer recordId);
    
    // 更新健康记录
    boolean updateHealthRecord(HealthRecord healthRecord);
    
    // 根据ID查询健康记录
    HealthRecord getHealthRecordById(Integer recordId);
    
    // 根据宠物ID查询所有健康记录
    List<HealthRecord> getHealthRecordsByPetId(Integer petId);
    
    // 查询所有健康记录
    List<HealthRecord> getAllHealthRecords();
} 