package com.aboutTime.infra;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private static final String SEPARATOR = "/";
    private static final String SEPARATOR_CODE = "%2F";
    private static final String HTTPS_URI_PREFIX = "https://";

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile file) {
        var fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        try {
            var objectMetadata = getObjectMetadataFromFile(file);
            var inputStream = file.getInputStream();
            amazonS3.putObject(bucket, fileName, inputStream, objectMetadata);
        } catch(IOException e) {
            throw new IllegalStateException("Invalid File", e);
        } catch (AmazonS3Exception e) {
            throw new IllegalStateException("Failed to upload file to S3", e);
        }
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    public void remove(String fileUri) {
        var fileNameStartIndex = fileUri.indexOf(SEPARATOR, HTTPS_URI_PREFIX.length() + 1);
        var fileName = fileUri.substring(fileNameStartIndex + 1).replace(SEPARATOR_CODE, SEPARATOR);

        try {
            amazonS3.deleteObject(bucket, fileName);
        } catch (AmazonS3Exception e) {
            throw new IllegalStateException("Failed to remove file from S3", e);
        }
    }

    private ObjectMetadata getObjectMetadataFromFile(final MultipartFile imageFile) {
        var objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(imageFile.getContentType());
        objectMetadata.setContentLength(imageFile.getSize());
        return objectMetadata;
    }

}
