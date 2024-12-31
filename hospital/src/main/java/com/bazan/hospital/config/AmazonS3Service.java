package com.bazan.hospital.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@Slf4j
public class AmazonS3Service implements IAmazonS3Service {

    private final static String S3_DIRECTORY = "hospital";
    private final static String S3_REGION = "us-east-1";

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.s3.accessKey}")
    private String accessKey;

    @Value("${aws.s3.secretKey}")
    private String secretKey;

    private S3Client s3Client;

    @PostConstruct
    private void initialize() {
        s3Client = S3Client.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)
                )).build();
    }

    @Override
    public String uploadFileUrl(MultipartFile multipartFile) throws FileUploadException {
        try {
            String filePath = S3_DIRECTORY + "/" + multipartFile.getOriginalFilename();
            String contentType = multipartFile.getContentType();
            Long contentLength = multipartFile.getSize();

            var objectBuilder = PutObjectRequest
                    .builder()
                    .bucket(bucketName)
                    .key(filePath)
                    .contentType(contentType)
                    .contentLength(contentLength)
                    .build();

            var requestBody = RequestBody.fromBytes(multipartFile.getBytes());

            s3Client.putObject(objectBuilder, requestBody);

            return "https://" + bucketName + ".s3." + S3_REGION + ".amazonaws.com/" + filePath;
        } catch (Exception e) {
            log.error("Error occurred: {}", e.getMessage());
            throw new FileUploadException("Error occurred in file upload ==> "+e.getMessage());
        }
    }
}
