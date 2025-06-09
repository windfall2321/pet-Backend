package web.petbackend.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import web.petbackend.entity.Pet;
import web.petbackend.mapper.PetMapper;
import web.petbackend.mapper.UserMapper;
import web.petbackend.service.PetService;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;

@Service
public class PetServiceImpl implements PetService {

    @Value("${upload.petImage.path}")
    private String uploadPath;

    @Value("${upload.petImage.url-prefix}")
    private String urlPrefix;

    @Resource
    private PetMapper petMapper;

    @Override
    public Pet addPet(Pet pet) {
    petMapper.insert(pet);
    return pet;
    }

    @Override
    public Pet updatePet(Pet pet) {
        petMapper.update(pet);
        return pet;
    }

    @Override
    public void deletePet(int petId) {
        petMapper.delete(petId);
    }

    @Override
    public List<Pet> getPets() {
        return petMapper.selectAll();

    }

    @Override
    public Pet selectById(int petId) {
        return petMapper.selectById(petId);
    }

    @Override
    public List<Pet> selectByOwnerId(int ownerId) {
        return petMapper.selectByOwnerId(ownerId);
    }

    @Override
    public void deleteByOwnerId(int ownerId) {
        petMapper.deleteByOwnerId(ownerId);

    }

    @Override
    public Pet uploadImage(Integer petId, MultipartFile file) {
        // 1. 检查用户是否存在
        Pet pet = petMapper.selectById(petId);
        if (pet == null) {
            throw new RuntimeException("用户不存在");
        }

        // 2. 检查文件类型
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new RuntimeException("只能上传图片文件");
        }

        // 3. 生成文件名
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : ".jpg";
        String filename = UUID.randomUUID().toString() + extension;

        // 4. 确保目录存在
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 5. 保存文件
        File destFile = new File(uploadPath + filename);
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            throw new RuntimeException("保存文件失败", e);
        }

        // 6. 删除旧头像
        if (pet.getImage() != null) {
            String oldProfilePath = pet.getImage().replace(urlPrefix, "");
            File oldFile = new File(uploadPath + oldProfilePath);
            if (oldFile.exists()) {
                oldFile.delete();
            }
        }

        // 7. 更新用户头像URL
        String profileUrl = urlPrefix + filename;
        pet.setImage(profileUrl);
        petMapper.update(pet);

        return pet;
    }

    @Override
    public void deleteImage(Integer userId) {
        // 1. 获取用户信息
        Pet pet = petMapper.selectById(userId);
        if (pet == null) {
            throw new RuntimeException("用户不存在");
        }

        // 2. 删除本地文件
        if (pet.getImage() != null) {
            String profilePath = pet.getImage().replace(urlPrefix, "");
            File file = new File(uploadPath + profilePath);
            if (file.exists()) {
                file.delete();
            }
        }

        // 3. 清除数据库中的头像URL
        pet.setImage(null);
        petMapper.update(pet);
    }

}
