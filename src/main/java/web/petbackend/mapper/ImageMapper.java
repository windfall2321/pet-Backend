package web.petbackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import web.petbackend.entity.Image;
import java.util.List;

@Mapper
public interface ImageMapper {
    Image selectImageById(@Param("imageId") Integer imageId);
    List<Image> selectImagesByTopicId(@Param("topicId") Integer topicId);
    List<Image> selectImagesByCommentId(@Param("commentId") Integer commentId);
    void insertImage(Image image);
    void deleteImageById(@Param("imageId") Integer imageId);
}
