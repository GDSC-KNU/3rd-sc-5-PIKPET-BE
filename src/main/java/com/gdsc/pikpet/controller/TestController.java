package com.gdsc.pikpet.controller;

//import com.gdsc.pikpet.config.StorageConfig;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final Storage storage;

    @Value("${gcs.bucket.name}")
    private String bucketName;
    @GetMapping("/")
    public String test() {
        return "Hello World!";
    }

    @GetMapping("/testImage")
    public String testImage() throws IOException {
        // StorageOptions 생성 (인증 정보 설정 필요)
        Blob blob = storage.get(bucketName, "spring.png");
        blob.downloadTo(Paths.get("TestImage.png"));

        // test시 resource 필드에 이미지+json 필요
        BlobInfo blobInfo =storage.create(
                BlobInfo.newBuilder(bucketName, "springTest.png")
                        .setContentType("png")
                        .build(),
                new FileInputStream("TestImage.png"));

        // 업로드 성공 시 메시지 반환
        return "이미지 다운로드 완료: " + blob.getName();
    }
}
