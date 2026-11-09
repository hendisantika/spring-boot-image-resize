package id.my.hendisantika.imageresizer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-image-resize
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/11/24
 * Time: 06.23
 * To change this template use File | Settings | File Templates.
 */
@Data
@Component
@ConfigurationProperties("aws")
public class AwsProperties {
    private String access;
    private String secret;
    private String accountNo;

    private S3Properties s3;

    @Data
    public static class S3Properties {
        private String bucket;
        private String region;
    }
}
