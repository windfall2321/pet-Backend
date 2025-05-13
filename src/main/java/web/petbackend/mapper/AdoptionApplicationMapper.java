package web.petbackend.mapper;

import web.petbackend.entity.AdoptionApplication;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AdoptionApplicationMapper {
    int insert(AdoptionApplication application);
    AdoptionApplication selectById(Integer applicationId);
    List<AdoptionApplication> selectByAdoptionId(Integer adoptionId);
    List<AdoptionApplication> selectAll();
    int update(AdoptionApplication application);
    int deleteById(Integer applicationId);
}
