package org.example.apps.storage.service;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author rival
 * @since 2023-12-30
 */
@Service
@Slf4j
public class MinioStorageService {
    private final MinioClient minioClient;
    private final String bucketName;

    public MinioStorageService(
        @Value("${minio.endpoint}") String endpoint,
        @Value("${minio.accessKey}") String accessKey,
        @Value("${minio.secretKey}") String secretKey,
        @Value("${minio.bucketName}") String bucketName
    ) {
        minioClient = MinioClient.builder()
            .endpoint(endpoint)
            .credentials(accessKey, secretKey)
            .build();
        this.bucketName = bucketName;
    }

    public void upload(String objectName, String contentType, byte[] data) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        // ObjectMetadata metadata = new ObjectMetadata();
        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .contentType(contentType)
                .stream(new ByteArrayInputStream(data), data.length, -1)
                .build()
        );
    }

    public byte[] download(String objectName) {

        try (InputStream stream = minioClient.getObject(
            GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build()
        )) {
            return stream.readAllBytes();
        } catch (Exception e) {
            log.error("Failed To download File : " + objectName, e);
            throw new RuntimeException(e);
        }
    }

    public String getPresignedUrl(String objectName) throws RuntimeException {
        try {
            GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName)
                .object(objectName)
                .expiry(24 * 60 * 60)
                .build();
            return minioClient.getPresignedObjectUrl(args);
        }catch(Exception e){
            log.error("Failed To download File : " + objectName, e);
            throw new RuntimeException(e);
        }
    }


    public void delete(String objectName) throws Exception {

        minioClient.removeObject(
            RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build()
        );
    }


}
