package web.petbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import web.petbackend.entity.Topic;
import web.petbackend.mapper.TopicMapper;
import web.petbackend.service.TopicService;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public Topic getTopicById(Integer id) {
        return topicMapper.selectTopicById(id);
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicMapper.selectAllTopics();
    }

    @Override
    public void addTopic(Topic topic) {
        topicMapper.insertTopic(topic);
    }

    @Override
    public void updateTopic(Topic topic) {
        topicMapper.updateTopic(topic);
    }

    @Override
    public void deleteTopic(Integer id) {
        topicMapper.deleteTopic(id);
    }
}
