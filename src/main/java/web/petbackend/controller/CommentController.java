package web.petbackend.controller;

import web.petbackend.entity.ApiResponse;
import web.petbackend.entity.Comment;
import web.petbackend.service.CommentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public ApiResponse<Comment> getComment(@PathVariable Integer id) {
        Comment comment = commentService.getCommentById(id);
        return ApiResponse.success("获取评论成功", comment);
    }

    @GetMapping("/list/{topicId}")
    public ApiResponse<List<Comment>> getCommentsByTopicId(@PathVariable Integer topicId) {
        List<Comment> comments = commentService.getCommentsByTopicId(topicId);
        return ApiResponse.success("获取话题评论成功", comments);
    }

    @PostMapping("/add")
    public ApiResponse<String> addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return ApiResponse.success("添加评论成功", "评论已成功添加");
    }

    @PutMapping("/update")
    public ApiResponse<String> updateComment(@RequestBody Comment comment) {
        commentService.updateComment(comment);
        return ApiResponse.success("更新评论成功", "评论已成功更新");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ApiResponse.success("删除评论成功", "评论已成功删除");
    }
}
