package web.petbackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import web.petbackend.entity.Comment;
import java.util.List;

@Mapper
public interface CommentMapper {
    Comment selectCommentById(@Param("id") Integer id);
    List<Comment> selectCommentsByTopicId(@Param("topicId") Integer topicId);
    void insertComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(@Param("id") Integer id);
    List<Integer> selectImageIdsByCommentId(@Param("id") Integer id);
}
