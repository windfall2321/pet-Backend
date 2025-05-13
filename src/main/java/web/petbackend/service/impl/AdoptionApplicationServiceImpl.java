package web.petbackend.service.impl;

import web.petbackend.entity.AdoptionApplication;
import web.petbackend.mapper.AdoptionApplicationMapper;
import web.petbackend.service.AdoptionApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionApplicationServiceImpl implements AdoptionApplicationService {

    @Autowired
    private AdoptionApplicationMapper applicationMapper;

    @Override
    public int addApplication(AdoptionApplication application) {
        return applicationMapper.insert(application);
    }

    @Override
    public AdoptionApplication getApplicationById(Integer id) {
        return applicationMapper.selectById(id);
    }

    @Override
    public List<AdoptionApplication> getApplicationsByAdoptionId(Integer adoptionId) {
        return applicationMapper.selectByAdoptionId(adoptionId);
    }

    @Override
    public List<AdoptionApplication> getAllApplications() {
        return applicationMapper.selectAll();
    }

    @Override
    public int updateApplication(AdoptionApplication application) {
        return applicationMapper.update(application);
    }

    @Override
    public int deleteApplication(Integer id) {
        return applicationMapper.deleteById(id);
    }
}
