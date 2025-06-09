package web.petbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.petbackend.entity.Comment;
import web.petbackend.entity.Topic;
import web.petbackend.mapper.CommentMapper;
import web.petbackend.service.CommentService;

import web.petbackend.service.ImageService;  // 导入 ImageService
import web.petbackend.utils.UserContextHolder;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ImageService imageService;  // 注入 ImageService

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
        Integer userId = UserContextHolder.getUserId();
        comment.setUserId(userId);
        commentMapper.insertComment(comment);
    }

    @Override
    public List<Comment> getAllCommentsByUserId(Integer UserId) {
        return commentMapper.selectByUserId(UserId);
    }
    @Override
    public void updateComment(Comment comment) {
        commentMapper.updateComment(comment);
    }

    @Override
    public void deleteComment(Integer id) {

        List<Integer> imageIds = commentMapper.selectImageIdsByCommentId(id);

        // 删除与评论关联的每张图片
        for (Integer imageId : imageIds) {
            imageService.deleteImageById(imageId);  // 调用 ImageService 的方法删除图片
        }

        // 然后删除评论
        commentMapper.deleteComment(id);
    }
}
