package web.petbackend.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import web.petbackend.entity.User;
import web.petbackend.mapper.UserMapper;
import web.petbackend.service.UserService;
import web.petbackend.utils.JwtUtil;

import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Value("${upload.profile.path}")
    private String uploadPath;

    @Value("${upload.profile.url-prefix}")
    private String urlPrefix;
    
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    @Override
    public User register(User user) {
        // 检查用户名是否已存在
        User existingUser = userMapper.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("用户名已存在");
        }
        
        // 加密密码
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        
        // 保存用户
        userMapper.insert(user);
        
        // 设置登录状态为在线
        userMapper.updateLoginStatus(user.getUserId(), 1);
        
        return user;
    }
    
    @Override
    public String login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null || !DigestUtils.md5Hex(password).equals(user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        
        // 更新登录状态
        userMapper.updateLoginStatus(user.getUserId(), 1);

        // 生成JWT token
        return JwtUtil.createJWT(user.getUserId());
    }
    
    @Override
    public User getUserInfo(Integer userId) {
        return userMapper.findById(userId);
    }

    @Override
    public void logout(Integer userId) {
        userMapper.updateLoginStatus(userId, 0);
    }

    @Override
    public User updateUserInfo(Integer userId, User user) {
        // 检查用户是否存在
        User existingUser = userMapper.findById(userId);
        if (existingUser == null) {
            throw new RuntimeException("用户不存在");
        }

        // 如果要修改用户名，检查新用户名是否已存在
        if (user.getUsername() != null && !user.getUsername().equals(existingUser.getUsername())) {
            User userWithNewUsername = userMapper.findByUsername(user.getUsername());
            if (userWithNewUsername != null) {
                throw new RuntimeException("用户名已存在");
            }
        }

        // 修改的密码也加密
        if (user.getPassword() != null ) {
            // 加密密码
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        }

        // 更新用户信息
        userMapper.updateUserInfo(userId, user);
        
        // 返回更新后的用户信息
        return userMapper.findById(userId);
    }

    @Override
    public User uploadProfile(Integer userId, MultipartFile file) {
        // 1. 检查用户是否存在
        User user = userMapper.findById(userId);
        if (user == null) {
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
        if (user.getProfile() != null) {
            String oldProfilePath = user.getProfile().replace(urlPrefix, "");
            File oldFile = new File(uploadPath + oldProfilePath);
            if (oldFile.exists()) {
                oldFile.delete();
            }
        }

        // 7. 更新用户头像URL
        String profileUrl = urlPrefix + filename;
        user.setProfile(profileUrl);
        userMapper.updateUserInfo(userId, user);

        return user;
    }

    @Override
    public void deleteProfile(Integer userId) {
        // 1. 获取用户信息
        User user = userMapper.findById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        // 2. 删除本地文件
        if (user.getProfile() != null) {
            String profilePath = user.getProfile().replace(urlPrefix, "");
            File file = new File(uploadPath + profilePath);
            if (file.exists()) {
                file.delete();
            }
        }

        // 3. 清除数据库中的头像URL
        user.setProfile(null);
        userMapper.updateUserInfo(userId, user);
    }
} 