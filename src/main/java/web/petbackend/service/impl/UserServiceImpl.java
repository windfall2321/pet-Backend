package web.petbackend.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.petbackend.entity.User;
import web.petbackend.mapper.UserMapper;
import web.petbackend.service.UserService;
import web.petbackend.utils.JwtUtil;

import java.security.Key;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    //private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    @Override
    public User register(User user) {
        // 检查用户名是否已存在
        User existingUser = userMapper.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("用户名已存在");
        }
        
        // 加密密码
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // 保存用户
        userMapper.insert(user);
        return user;
    }
    
    @Override
    public String login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null || !password.equals(user.getPassword())) {
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

        // 更新用户信息
        userMapper.updateUserInfo(userId, user);
        
        // 返回更新后的用户信息
        return userMapper.findById(userId);
    }
} 