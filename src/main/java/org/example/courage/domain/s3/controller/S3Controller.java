package org.example.courage.domain.s3.controller;

import lombok.RequiredArgsConstructor;
import org.example.courage.domain.s3.service.S3Service;
import org.example.courage.global.BaseResponseParent;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;


    @PostMapping
    public String s3Upload(@RequestPart(value = "image", required = false) MultipartFile image) {
        if (image == null || image.isEmpty()) {
            return "s3://haso-bucket/jeknf.jpg";
        }

        String profileImage = s3Service.upload(image);
        return profileImage;
    }


    @DeleteMapping
    public BaseResponseParent s3Delete(@RequestParam String imageUrl) {
        s3Service.deleteImageFromS3(imageUrl);
        return BaseResponseParent.ok("이미지 삭제 성공");
    }

}