package id.my.hendisantika.imageresizer.controller;

import id.my.hendisantika.imageresizer.service.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-image-resize
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/11/24
 * Time: 06.14
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class S3Controller {

    private final AmazonS3Service amazonS3Service;

    @PostMapping("/upload")
    public String uploadImageFileToS3(@RequestParam("file") MultipartFile file) {
        String result = amazonS3Service.uploadFile(file);
        log.info("Upload image to S3: {}", result);
        return "redirect:/";
    }
}
