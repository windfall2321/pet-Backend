package web.petbackend.service;

import web.petbackend.entity.User;

public interface UserService {
    User register(User user);
    String login(String username, String password);
    User getUserInfo(Integer userId);
    void logout(Integer userId);
} 