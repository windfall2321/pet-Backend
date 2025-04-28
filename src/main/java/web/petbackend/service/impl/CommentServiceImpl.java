package web.petbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.petbackend.entity.Comment;
import web.petbackend.mapper.CommentMapper;
import web.petbackend.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Comment getCommentById(Integer id) {
        return commentMapper.selectCommentById(id);
    }

    @Override
    public List<Comment> getCommentsByTopicId(Integer topicId) {
        return commentMapper.selectCommentsByTopicId(topicId);
    }

    @Override
    public void addComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        commentMapper.updateComment(comment);
    }

    @Override
    public void deleteComment(Integer id) {
        commentMapper.deleteComment(id);
    }
}
