package org.example.courage.domain.s3.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.courage.domain.s3.entity.ImageEntity;
import org.example.courage.domain.s3.repository.S3JpaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Slf4j
@Component
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;
    private final S3JpaRepository s3JpaRepository;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;



    public String upload(MultipartFile image) {
        if (image == null || image.isEmpty() || image.getOriginalFilename() == null) {
            throw new AmazonS3Exception("이미지가 비워져 있거나 유효한 파일이 아닙니다.");
        }

        String imageUrl = this.uploadImage(image);
        s3JpaRepository.save(ImageEntity.builder()
                .imageUrl(imageUrl)
                .build()
        );

        return imageUrl;
    }


    private String uploadImage(MultipartFile image) {
        this.validateImageFileExtension(Objects.requireNonNull(image.getOriginalFilename()));
        try {
            return this.uploadImageToS3(image);
        } catch (IOException e) {
            throw new AmazonS3Exception("이미지 업로드 에러");
        }
    }

    private void validateImageFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1) {
            throw new AmazonS3Exception("확장자 에러");
        }

        String extension = filename.substring(lastDotIndex + 1).toLowerCase();
        List<String> allowedExtentionList = Arrays.asList("jpg", "jpeg", "png", "gif");

        if (!allowedExtentionList.contains(extension)) {
            throw new AmazonS3Exception("잘못된 이미지 확장자");
        }
    }


    private String uploadImageToS3(MultipartFile image) throws IOException {
        String originalFilename = image.getOriginalFilename();
        assert originalFilename != null : "파일명이 null입니다."; // null 체크

        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String s3FileName = UUID.randomUUID().toString().substring(0, 10) + originalFilename;

        InputStream is = image.getInputStream();
        byte[] bytes = IOUtils.toByteArray(is);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/" + extension);
        metadata.setContentLength(bytes.length);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        try {
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucketName, s3FileName, byteArrayInputStream, metadata);
            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            log.error("S3 업로드 에러", e);
            throw new AmazonS3Exception("S3 업로드 중 오류가 발생했습니다. 다시 시도해 주세요.");
        } finally {
            byteArrayInputStream.close();
            is.close();
        }
        return amazonS3.getUrl(bucketName, s3FileName).toString();
    }



    public void deleteImageFromS3(String imageAddress) {
        String key = getKeyFromImageAddress(imageAddress);
        try {
            amazonS3.deleteObject(new DeleteObjectRequest(bucketName, key));
        } catch (Exception e) {
            throw new AmazonS3Exception("이미지 삭제 에러");
        }
    }


    private String getKeyFromImageAddress(String imageAddress) {
        try {
            URL url = new URL(imageAddress);
            String decodingKey = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8);
            return decodingKey.substring(1);
        } catch (MalformedURLException e) {
            throw new AmazonS3Exception("이미지 삭제 에러");
        }
    }
}
