package web.petbackend.service;

import org.apache.ibatis.annotations.Param;
import web.petbackend.entity.Comment;
import web.petbackend.entity.Topic;

import java.util.List;

public interface CommentService {
    Comment getCommentById(Integer id);
    List<Comment> getCommentsByTopicId(Integer topicId);
    void addComment(Comment comment);

    void addminiComment(Comment comment);

    void updateComment(Comment comment);
    void deleteComment(Integer id);
    List<Comment> getAllCommentsByUserId(Integer UserId);
}
