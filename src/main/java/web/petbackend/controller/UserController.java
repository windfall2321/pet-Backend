package web.petbackend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import web.petbackend.entity.ApiResponse;
import web.petbackend.entity.User;
import web.petbackend.service.UserService;
import web.petbackend.utils.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.petbackend.config.exception.BusinessException;
import web.petbackend.config.exception.ErrorCode;
import web.petbackend.dto.UserLoginRequest;

@SecurityRequirement(name = "Bearer Token")
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
    public ApiResponse<User> getUserInfo() {
        Integer userId = UserContextHolder.getUserId();
        User user = userService.getUserInfo(userId);
        return ApiResponse.success("获取用户信息成功", user);
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout() {
        Integer userId = UserContextHolder.getUserId();
        userService.logout(userId);
        return ApiResponse.success("登出成功");
    }

    @PutMapping("/updateinfo")
    public ApiResponse<User> updateUserInfo(@RequestBody User user) {
        Integer userId = UserContextHolder.getUserId();
        User updatedUser = userService.updateUserInfo(userId, user);
        return ApiResponse.success("更新用户信息成功", updatedUser);
    }
} 