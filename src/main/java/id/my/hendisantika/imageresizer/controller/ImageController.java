package id.my.hendisantika.imageresizer.controller;

import id.my.hendisantika.imageresizer.model.ImageSettingsBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-image-resize
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/11/24
 * Time: 06.12
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestController
public class ImageController {

    private final static String SOURCE_IMG_DIR = "https://s3.eu-west-2.amazonaws.com/bucket-10/resources/sourceImages/";
    private final static String RESIZED_IMG_DIR = "https://s3.eu-west-2.amazonaws.com/bucket-10/resources/resizedImages/";

    /**
     * @param fileName - the name of the image file
     * @param typeName - the type of the image file (e.g. thumbnail)
     * @return ResponseEntity - the resized image
     */
    @RequestMapping(value = "/image/show/{typeName}/seo/{fileName}")
    public ResponseEntity<byte[]> requestImage(@PathVariable String fileName, @PathVariable String typeName) {
        ImageSettingsBuilder imageSettingsBuilder = new ImageSettingsBuilder();
        imageSettingsBuilder.buildImageSettings(typeName);
        byte[] resizedImage = this.getResizedImageFile(fileName, typeName);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resizedImage);
    }
}
