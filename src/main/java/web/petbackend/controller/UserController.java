package web.petbackend.controller;

import web.petbackend.entity.ApiResponse;
import web.petbackend.entity.User;
import web.petbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.petbackend.config.exception.BusinessException;
import web.petbackend.config.exception.ErrorCode;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ApiResponse<User> register(@RequestBody User user) {
        User registeredUser = userService.register(user);
        return ApiResponse.success("注册成功", registeredUser);
    }
    
    @PostMapping("/login")
    public ApiResponse<String> login(@RequestParam String username, @RequestParam String password) {
        try {
            String token = userService.login(username, password);
            return ApiResponse.success("登录成功", token);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.PASSWORD_ERROR.getCode(), "用户名或密码错误");
        }
    }
    
    @GetMapping("/info")
    public ApiResponse<User> getUserInfo(@RequestHeader("Authorization") String token) {
        // 这里需要解析token获取用户ID
        // 为了简化示例，假设token就是用户ID
        Integer userId = Integer.parseInt(token);
        User user = userService.getUserInfo(userId);
        return ApiResponse.success("获取用户信息成功", user);
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(@RequestHeader("Authorization") String token) {
        Integer userId = Integer.parseInt(token);
        userService.logout(userId);
        return ApiResponse.success("登出成功");
    }
} 