package web.petbackend.service.impl;

import web.petbackend.entity.User;
import web.petbackend.mapper.UserMapper;
import web.petbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    @Override
    public User register(User user) {
        // 检查用户名是否已存在
        User existingUser = userMapper.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("用户名已存在");
        }
        
        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // 保存用户
        userMapper.insert(user);
        return user;
    }
    
    @Override
    public String login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        
        // 更新登录状态
        userMapper.updateLoginStatus(user.getUserId(), 1);
        
        // 生成JWT token
        return Jwts.builder()
                .setSubject(user.getUserId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24小时过期
                .signWith(key)
                .compact();
    }
    
    @Override
    public User getUserInfo(Integer userId) {
        return userMapper.findById(userId);
    }

    @Override
    public void logout(Integer userId) {
        userMapper.updateLoginStatus(userId, 0);
    }
} 