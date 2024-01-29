package com.gdsc.pikpet.controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

@RestController
public class TestController {

    @GetMapping("/")
    public String test() {
        return "Hello World!";
    }

    @GetMapping("/testImage")
    public String testImage() throws IOException {
        String bucketName = "solution-challenge-bucket"; // Google Cloud Storage 버킷 이름
        String imageName = "good.jpg"; // 업로드할 이미지 파일 이름

        // 서비스 계정 키 파일 경로
        String jsonPath = "/Users/dbswodyd00/Desktop/github/3rd-sc-ex5-pikpet-Backend/src/main/resources/soc.json";
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        // StorageOptions 생성 (인증 정보 설정 필요)
        Blob blob = storage.get("solution-challenge-bucket", "spring.png");
        blob.downloadTo(Paths.get("TestImage"));


        // test시 resource 필드에 이미지 필요
        BlobInfo blobInfo =storage.create(
                BlobInfo.newBuilder("solution-challenge-bucket", "springTest.png")
                        .setContentType("png")
                        .build(),
                new FileInputStream("TestImage"));

        // 업로드 성공 시 메시지 반환
        return "이미지 다운로드 완료: " + blob.getName();
    }
}
