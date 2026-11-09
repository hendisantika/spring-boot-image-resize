package id.my.hendisantika.imageresizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
@Controller
@RequestMapping("/")
public class UploadImageController {

    @GetMapping
    public String uploadImage() {
        return "index";
    }
}
