package web.petbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.petbackend.entity.ApiResponse;
import web.petbackend.entity.Pet;
import web.petbackend.service.PetService;

import java.util.List;

@RestController
@RequestMapping("/api/pet")
public class PetController {
    
    @Autowired
    private PetService petService;
    
    @PostMapping("/add")
    public ApiResponse<Pet> addPet(@RequestBody Pet pet) {
        Pet savedPet = petService.addPet(pet);
        return ApiResponse.success("添加宠物成功", savedPet);
    }
    
    @PutMapping("/update")
    public ApiResponse<Pet> updatePet(@RequestBody Pet pet) {
        Pet updatedPet = petService.updatePet(pet);
        return ApiResponse.success("更新宠物信息成功", updatedPet);
    }
    
    @DeleteMapping("/delete")
    public ApiResponse<String> deletePet(@RequestParam int petId) {
        petService.deletePet(petId);
        return ApiResponse.success("删除宠物成功");
    }
    
    @GetMapping("/list")
    public ApiResponse<List<Pet>> getAllPets() {
        List<Pet> pets = petService.getPets();
        return ApiResponse.success("获取所有宠物列表成功", pets);
    }
    
    @GetMapping("/owner/{ownerId}")
    public ApiResponse<List<Pet>> getPetsByOwnerId(@PathVariable int ownerId) {
        List<Pet> pets = petService.selectByOwnerId(ownerId);
        return ApiResponse.success("获取指定用户的宠物列表成功", pets);
    }
    
    @DeleteMapping("/owner/{ownerId}")
    public ApiResponse<String> deletePetsByOwnerId(@PathVariable int ownerId) {
        petService.deleteByOwnerId(ownerId);
        return ApiResponse.success("删除指定用户的所有宠物成功");
    }

    @PostMapping(value = "/image/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<Pet> uploadImage(@RequestParam("petId") int petId,@RequestParam("file") MultipartFile file) {
        Pet pet = petService.uploadImage(petId, file);
        return ApiResponse.success("上传头像成功", pet);
    }

    @DeleteMapping("/image")
    public ApiResponse<String> deleteProfile(@RequestParam("petId") int petId) {
        petService.deleteImage(petId);
        return ApiResponse.success("删除头像成功");
    }
}
