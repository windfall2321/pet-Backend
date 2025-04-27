package web.petbackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import web.petbackend.entity.Topic;
import java.util.List;

@Mapper
public interface TopicMapper {
    Topic selectTopicById(@Param("id") Integer id);
    List<Topic> selectAllTopics();
    void insertTopic(Topic topic);
    void updateTopic(Topic topic);
    void deleteTopic(@Param("id") Integer id);
}
