package web.petbackend.service;

import web.petbackend.entity.Image;

import java.util.List;

public interface ImageService {
    void addImage(Image image);
    Image getImageById(Integer imageId);
    List<Image> getImagesByTopicId(Integer topicId);
    List<Image> getImagesByCommentId(Integer commentId);
    void deleteImageById(Integer imageId);
}
