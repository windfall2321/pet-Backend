package web.petbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import web.petbackend.entity.Comment;
import web.petbackend.entity.Topic;
import web.petbackend.mapper.TopicMapper;
import web.petbackend.service.TopicService;
import org.springframework.stereotype.Service;

import web.petbackend.service.CommentService;// 导入 CommentService
import web.petbackend.service.ImageService;  // 导入 ImageService
import web.petbackend.utils.UserContextHolder;

import java.util.List;
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private CommentService commentService;// 注入 CommentService

    @Autowired
    private ImageService imageService;  // 注入 ImageService

    @Override
    public Topic getTopicById(Integer id) {
        return topicMapper.selectTopicById(id);
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicMapper.selectAllTopics();
    }

    @Override
    public List<Topic> getAllTopicsByUserId(Integer UserId) {
        return topicMapper.selectByUserId(UserId);
    }

    @Override
    public void addTopic(Topic topic) {
        Integer userId = UserContextHolder.getUserId();
        topic.setUserId(userId);
        topicMapper.insertTopic(topic);
    }

    @Override
    public void updateTopic(Topic topic) {
        topicMapper.updateTopic(topic);
    }

    @Override
    public void deleteTopic(Integer id) {
        List<Integer> commentIds = topicMapper.selectCommentIdsByTopicId(id);

        // 删除每个评论及其依赖的图片
        for (Integer commentId : commentIds) {
            commentService.deleteComment(commentId);
        }
        List<Integer> imageIds = topicMapper.selectImageIdsByTopicId(id);

        // 删除每个评论及其依赖的图片
        for (Integer imageId : imageIds) {
            imageService.deleteImageById(imageId);
        }

        topicMapper.deleteTopic(id);
    }
}
