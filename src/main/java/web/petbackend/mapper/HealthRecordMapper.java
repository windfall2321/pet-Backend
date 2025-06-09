package web.petbackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import web.petbackend.entity.HealthRecord;
import java.util.List;

@Mapper
public interface HealthRecordMapper {
    // 添加健康记录
    int insert(HealthRecord healthRecord);
    
    // 根据ID删除健康记录
    int deleteById(Integer recordId);
    
    // 更新健康记录
    int update(HealthRecord healthRecord);
    
    // 根据ID查询健康记录
    HealthRecord selectById(Integer recordId);
    
    // 根据宠物ID查询所有健康记录
    List<HealthRecord> selectByPetId(Integer petId);
    
    // 查询所有健康记录
    List<HealthRecord> selectAll();
} 