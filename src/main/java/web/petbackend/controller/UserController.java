package web.petbackend.controller;

import web.petbackend.entity.User;
import web.petbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        User registeredUser = userService.register(user);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "注册成功");
        response.put("data", registeredUser);
        return response;
    }
    
    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password) {
        String token = userService.login(username, password);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "登录成功");
        response.put("token", token);
        return response;
    }
    
    @GetMapping("/info")
    public Map<String, Object> getUserInfo(@RequestHeader("Authorization") String token) {
        // 这里需要解析token获取用户ID
        // 为了简化示例，假设token就是用户ID
        Integer userId = Integer.parseInt(token);
        User user = userService.getUserInfo(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("data", user);
        return response;
    }

    @PostMapping("/logout")
    public Map<String, Object> logout(@RequestHeader("Authorization") String token) {
        Integer userId = Integer.parseInt(token);
        userService.logout(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "登出成功");
        return response;
    }
} 