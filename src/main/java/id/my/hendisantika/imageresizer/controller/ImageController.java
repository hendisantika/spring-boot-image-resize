package id.my.hendisantika.imageresizer.controller;

import id.my.hendisantika.imageresizer.model.ImageSettingsBuilder;
import id.my.hendisantika.imageresizer.service.ImageResizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

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

    /**
     * Check if resized file already exists, otherwise resize it
     *
     * @param fileName - the name of the image file
     * @param typeName - the type of the image file (e.g. thumbnail)
     * @return image - the resized image
     */
    private byte[] getResizedImageFile(String fileName, String typeName) {
        try {
            URL url = new URL(RESIZED_IMG_DIR + typeName + "/" + typeName + "-" + fileName);
            BufferedImage resizedBufferedImage = ImageIO.read(url);

            if (resizedBufferedImage != null) {
                log.info("Optimized image exists");
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(resizedBufferedImage, "jpg", os);

                return os.toByteArray();
            }
        } catch (IOException e) {
            log.error("The URL is not valid: {}", String.valueOf(e));
        }

        try {
            URL sourceImageUrl = new URL(SOURCE_IMG_DIR + fileName);
            BufferedImage sourceImage = ImageIO.read(sourceImageUrl);
            log.info("Optimized image does not exists");
            ImageResizer imageResizer = new ImageResizer();

            return imageResizer.resizeImage(sourceImage, typeName);

        } catch (IOException e) {
            log.error("The original image does not exist: {}", String.valueOf(e));
        }

        return null;
    }
}
