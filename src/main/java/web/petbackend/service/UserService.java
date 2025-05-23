package web.petbackend.service;

import org.springframework.web.multipart.MultipartFile;
import web.petbackend.entity.User;

public interface UserService {
    User register(User user);
    String login(String username, String password);
    User getUserInfo(Integer userId);
    void logout(Integer userId);
    User updateUserInfo(Integer userId, User user);
    
    /**
     * 上传用户头像
     * @param userId 用户ID
     * @param file 头像文件
     * @return 更新后的用户信息
     */
    User uploadProfile(Integer userId, MultipartFile file);

    /**
     * 删除用户头像
     * @param userId 用户ID
     */
    void deleteProfile(Integer userId);
} 