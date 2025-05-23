package web.petbackend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import web.petbackend.entity.ApiResponse;
import web.petbackend.entity.User;
import web.petbackend.service.UserService;
import web.petbackend.utils.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.petbackend.config.exception.BusinessException;
import web.petbackend.config.exception.ErrorCode;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/profile/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<User> uploadProfile(@RequestParam("file") MultipartFile file) {
        Integer userId = UserContextHolder.getUserId();
        User user = userService.uploadProfile(userId, file);
        return ApiResponse.success("上传头像成功", user);
    }

    @DeleteMapping("/profile")
    public ApiResponse<String> deleteProfile() {
        Integer userId = UserContextHolder.getUserId();
        userService.deleteProfile(userId);
        return ApiResponse.success("删除头像成功");
    }
} 