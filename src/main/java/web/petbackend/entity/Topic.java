package web.petbackend.entity;

import lombok.Data;
import java.util.Date;


@Data
public class Topic {
    private Integer topicId;     // 帖子ID
    private String title;        // 帖子标题
    private Integer userId;      // 发布用户ID
    private String content;      // 帖子内容
    private String tags;         // 标签
    private Date timestamp;      // 发布时间
}
