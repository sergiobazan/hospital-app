package com.bazan.hospital.config;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

public interface IAmazonS3Service {
    String uploadFileUrl(MultipartFile multipartFile) throws FileUploadException;
}
