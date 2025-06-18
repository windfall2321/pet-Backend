package web.petbackend.service;

import web.petbackend.entity.Topic;
import java.util.List;

public interface TopicService {
    Topic getTopicById(Integer id);
    List<Topic> getAllTopics();
    void addTopic(Topic topic);
    void addminiTopic(Topic topic);
    void updateTopic(Topic topic);
    void deleteTopic(Integer id);
    List<Topic> getAllTopicsByUserId(Integer UserId);
}

