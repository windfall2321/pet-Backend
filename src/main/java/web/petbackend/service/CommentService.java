package web.petbackend.service;

import web.petbackend.entity.Comment;
import java.util.List;

public interface CommentService {
    Comment getCommentById(Integer id);
    List<Comment> getCommentsByTopicId(Integer topicId);
    void addComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(Integer id);
}
