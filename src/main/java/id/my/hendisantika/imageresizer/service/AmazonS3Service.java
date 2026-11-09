package id.my.hendisantika.imageresizer.service;

import id.my.hendisantika.imageresizer.config.AwsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.StorageClass;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-image-resize
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/11/24
 * Time: 06.24
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Service
public class AmazonS3Service {

    private static final String BASE_PREFIX_IMAGE_NAME_TAG = "nakes/photos/";
    private final S3Client s3Client;
    private final AwsProperties awsProperties;

    public AmazonS3Service(@Qualifier("s3Client") S3Client s3Client, AwsProperties awsProperties) {
        this.s3Client = s3Client;
        this.awsProperties = awsProperties;
    }

    public String uploadFile(MultipartFile multipartFile) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(awsProperties.getS3().getBucket())
                    .key(BASE_PREFIX_IMAGE_NAME_TAG+multipartFile.getOriginalFilename())
                    .contentLength(multipartFile.getSize())
                    .storageClass(StorageClass.STANDARD)
                    .build();
            log.info("multipartFile.getSize() -> {}", multipartFile.getSize());
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(multipartFile.getInputStream().readAllBytes()));
            log.info("Upload file to AWS S3 Successfully");
            return multipartFile.getOriginalFilename() + " Uploaded.";
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
