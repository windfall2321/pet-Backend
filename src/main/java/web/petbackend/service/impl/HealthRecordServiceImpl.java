package web.petbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.petbackend.entity.HealthRecord;
import web.petbackend.mapper.HealthRecordMapper;
import web.petbackend.service.HealthRecordService;
import java.util.List;

@Service
public class HealthRecordServiceImpl implements HealthRecordService {
    
    @Autowired
    private HealthRecordMapper healthRecordMapper;
    
    @Override
    public boolean addHealthRecord(HealthRecord healthRecord) {
        return healthRecordMapper.insert(healthRecord) > 0;
    }
    
    @Override
    public boolean deleteHealthRecord(Integer recordId) {
        return healthRecordMapper.deleteById(recordId) > 0;
    }
    
    @Override
    public boolean updateHealthRecord(HealthRecord healthRecord) {
        return healthRecordMapper.update(healthRecord) > 0;
    }
    
    @Override
    public HealthRecord getHealthRecordById(Integer recordId) {
        return healthRecordMapper.selectById(recordId);
    }
    
    @Override
    public List<HealthRecord> getHealthRecordsByPetId(Integer petId) {
        return healthRecordMapper.selectByPetId(petId);
    }
    
    @Override
    public List<HealthRecord> getAllHealthRecords() {
        return healthRecordMapper.selectAll();
    }
} 