package web.petbackend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import web.petbackend.entity.ApiResponse;
import web.petbackend.entity.User;
import web.petbackend.service.UserService;
import web.petbackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.petbackend.config.exception.BusinessException;
import web.petbackend.config.exception.ErrorCode;

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
    public ApiResponse<User> getUserInfo(@RequestHeader("Authorization") String token) {
        try {
            Integer userId = JwtUtil.parseToken(token);
            System.out.println(userId);
            User user = userService.getUserInfo(userId);
            return ApiResponse.success("获取用户信息成功", user);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR.getCode(), "未登录或登录已过期");
        }
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(@RequestHeader("Authorization") String token) {
        try {
            Integer userId = JwtUtil.parseToken(token);
            userService.logout(userId);
            return ApiResponse.success("登出成功");
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR.getCode(), "未登录或登录已过期");
        }
    }

    @PutMapping("/updateinfo")
    public ApiResponse<User> updateUserInfo(@RequestHeader("Authorization") String token, @RequestBody User user) {
        try {
            Integer userId = JwtUtil.parseToken(token);
            System.out.println(userId);
            User updatedUser = userService.updateUserInfo(userId, user);
            return ApiResponse.success("更新用户信息成功", updatedUser);
        } catch (RuntimeException e) {
            throw new BusinessException(ErrorCode.PARAM_ERROR.getCode(), e.getMessage());
        }
    }
} 