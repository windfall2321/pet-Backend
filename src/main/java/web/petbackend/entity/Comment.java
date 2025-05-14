package web.petbackend.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Comment {
    private Integer commentId;
    private Integer userId;
    private Integer topicId;
    private String content;
    private Date timestamp;
}
