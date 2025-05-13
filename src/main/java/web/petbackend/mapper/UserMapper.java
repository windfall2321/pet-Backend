package web.petbackend.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import web.petbackend.entity.User;

@Mapper
public interface UserMapper {
    User findByUsername(@Param("username") String username);
    void insert(User user);
    User findById(@Param("userId") Integer userId);
    void updateLoginStatus(@Param("userId") Integer userId, @Param("isOn") Integer isOn);
    void updateUserInfo(@Param("userId") Integer userId, @Param("user") User user);
} 