package web.petbackend.controller;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.petbackend.entity.ApiResponse;
import web.petbackend.entity.PageResult;
import web.petbackend.entity.PetEncyclopedia;
import web.petbackend.mapper.PetEncyMapper;
import web.petbackend.service.PetEncyService;

import java.util.List;

@RestController
@RequestMapping("/api/petEncy")
public class PetEncyController {
    @Autowired
    private PetEncyService petEncyService;

    @GetMapping("/all")
    public ApiResponse<List<PetEncyclopedia>> getAllPetEncy() {
        List<PetEncyclopedia> petEncyList = petEncyService.getAllPetEncy();
        return  ApiResponse.success("返回不分页宠物百科数据",petEncyList);
    }

    // 增加一条宠物百科数据
    @PostMapping("/add")
    public ApiResponse<String> addPetEncy(@RequestBody PetEncyclopedia petEncyclopedia) {
        petEncyService.addPetEncy(petEncyclopedia);
        return ApiResponse.success("添加成功");
    }

    // 删除一条宠物百科数据
    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deletePetEncy(@PathVariable("id") Integer petEncyId) {
        petEncyService.deletePetEncy(petEncyId);
        return ApiResponse.success("删除成功");
    }

    // 更新一条宠物百科数据
    @PutMapping("/update")
    public ApiResponse<String> updatePetEncy(@RequestBody PetEncyclopedia petEncyclopedia) {
        petEncyService.updatePetEncy(petEncyclopedia);
        return ApiResponse.success("更新成功");
    }

    @GetMapping("/page")
    public ApiResponse<PageResult<PetEncyclopedia>> getAllItems(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String bodily_form) {

        PageResult<PetEncyclopedia> result = petEncyService.getPetEncyByPageWithFilters(pageNum, pageSize, category, bodily_form);
        return ApiResponse.success("返回分页宠物百科数据", result);
    }



}