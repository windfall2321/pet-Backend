package web.petbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import web.petbackend.entity.Image;
import web.petbackend.mapper.ImageMapper;
import web.petbackend.service.ImageService;


import java.io.File;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Value("${upload.image.path}")
    private String uploadPath;

    @Value("${upload.image.url-prefix}")
    private String urlPrefix;

    @Override
    public void addImage(Image image) {
        imageMapper.insertImage(image);  // 插入图片记录
    }

    @Override
    public Image getImageById(Integer imageId) {
        return imageMapper.selectImageById(imageId);  // 查询单张图片
    }

    @Override
    public List<Image> getImagesByTopicId(Integer topicId) {
        return imageMapper.selectImagesByTopicId(topicId);  // 根据帖子ID查询图片
    }

    @Override
    public List<Image> getImagesByCommentId(Integer commentId) {
        return imageMapper.selectImagesByCommentId(commentId);  // 根据评论ID查询图片
    }

    @Override
    public void deleteImageById(Integer imageId) {
        // 1. 获取图片URL
        Image image = imageMapper.selectImageById(imageId);
        if (image != null && image.getImageUrl() != null) {
            // 2. 删除本地文件
            String relativePath = image.getImageUrl().replace(urlPrefix, "");
            File file = new File(uploadPath + relativePath);

            if (file.exists()) {
                boolean deleted = file.delete();
                if (!deleted) {
                    throw new RuntimeException("删除本地文件失败：" + file.getAbsolutePath());
                }
            }
        }

        // 3. 删除数据库记录
        imageMapper.deleteImageById(imageId);
    }
}
