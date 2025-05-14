package web.petbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.petbackend.entity.Image;
import web.petbackend.service.ImageService;
import org.springframework.http.MediaType;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Value("${upload.image.path}")
    private String uploadPath;

    @Value("${upload.image.url-prefix}")
    private String urlPrefix;


    // 上传图片接口
//    @PostMapping("/upload")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public String uploadImage(@RequestParam("file") MultipartFile file,
                              @RequestParam("type") String type,
                              @RequestParam(value = "topicId", required = false) Integer topicId,
                              @RequestParam(value = "commentId", required = false) Integer commentId) throws IOException {
        if (file.isEmpty()) {
            return "上传失败，文件为空";
        }

        // 保存到本地
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        File dest = new File(uploadPath + filename);
        file.transferTo(dest);

        // 保存到数据库
        Image image = new Image();
        image.setTopicId(topicId);
        image.setCommentId(commentId);
        image.setType(type);
        image.setImageUrl(urlPrefix + filename);
        imageService.addImage(image);

        return "上传成功，图片地址：" + urlPrefix + filename;
    }
    // 获取单张图片接口
    @GetMapping("/{imageId}")
    public Image getImage(@PathVariable Integer imageId) {
        return imageService.getImageById(imageId);
    }

    // 根据帖子ID获取图片接口
    @GetMapping("/topic/{topicId}")
    public List<Image> getImagesByTopic(@PathVariable Integer topicId) {
        return imageService.getImagesByTopicId(topicId);
    }

    // 根据评论ID获取图片接口
    @GetMapping("/comment/{commentId}")
    public List<Image> getImagesByComment(@PathVariable Integer commentId) {
        return imageService.getImagesByCommentId(commentId);
    }

    // 删除图片接口
    @DeleteMapping("/delete/{imageId}")
    public String deleteImage(@PathVariable Integer imageId) {
        imageService.deleteImageById(imageId);
        return "删除成功";
    }
}
