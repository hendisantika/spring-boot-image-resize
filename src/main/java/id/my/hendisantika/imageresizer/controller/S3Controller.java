package id.my.hendisantika.imageresizer.controller;

import org.springframework.web.bind.annotation.RestController;

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
@RestController
public class S3Controller {
    private final static String SOURCE_IMG_BUCKET = "bucket-10/resources/sourceImages";
    private final static String RESIZED_IMG_BUCKET = "bucket-10/resources/resizedImages";
    private final static String ACCESS_KEY = ""; //put your AWS access key here
    private final static String SECRET_KEY = ""; //put your AWS secret key here
    private final static String S3_REGION = "eu-west-2";
}
