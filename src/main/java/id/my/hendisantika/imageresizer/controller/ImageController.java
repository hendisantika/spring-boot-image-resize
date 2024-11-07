package id.my.hendisantika.imageresizer.controller;

import lombok.extern.slf4j.Slf4j;
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
}
