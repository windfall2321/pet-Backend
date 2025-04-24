package web.petbackend.entity;

import lombok.Data;
import java.util.Date;

@Data
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String profile;
    private Integer isOn;
    private Date lastLogin;
} 