package web.petbackend.controller;

import web.petbackend.entity.ApiResponse;
import web.petbackend.entity.Topic;
import web.petbackend.service.TopicService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/{id}")
    public ApiResponse<Topic> getTopic(@PathVariable Integer id) {
        Topic topic = topicService.getTopicById(id);
        return ApiResponse.success("获取成功", topic);
    }

    @GetMapping("/list")
    public ApiResponse<List<Topic>> getAllTopics() {
        List<Topic> topics = topicService.getAllTopics();
        return ApiResponse.success("获取所有话题成功", topics);
    }

    @PostMapping("/add")
    public ApiResponse<String> addTopic(@RequestBody Topic topic) {
        topicService.addTopic(topic);
        return ApiResponse.success("添加成功", "话题已成功添加");
    }

    @PutMapping("/update")
    public ApiResponse<String> updateTopic(@RequestBody Topic topic) {
        topicService.updateTopic(topic);
        return ApiResponse.success("更新成功", "话题已成功更新");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteTopic(@PathVariable Integer id) {
        topicService.deleteTopic(id);
        return ApiResponse.success("删除成功", "话题已成功删除");
    }
}
