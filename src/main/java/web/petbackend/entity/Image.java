package web.petbackend.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Image {
    private Integer imageId;       // 图片ID
    private Integer topicId;       // 关联的帖子ID
    private Integer commentId;     // 关联的评论ID
    private String type;           // 图片类型 ('topic' 或 'comment')
    private String imageUrl;       // 图片的URL
}
