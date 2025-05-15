package web.petbackend.service;

import web.petbackend.entity.AdoptionApplication;
import java.util.List;

public interface AdoptionApplicationService {
    int addApplication(AdoptionApplication application);
    AdoptionApplication getApplicationById(Integer id);
    List<AdoptionApplication> getApplicationsByAdoptionId(Integer adoptionId);
    List<AdoptionApplication> getApplicationsByUserId(Integer userId);
    List<AdoptionApplication> getAllApplications();
    int updateApplication(AdoptionApplication application);
    int deleteApplication(Integer id);
}
