package com.example.demo;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucket;

    private final Storage storage;

    //test
    public void test(TestDto dto) throws IOException {
        log.info("bucket : {}", bucket );
        String uuid = UUID.randomUUID().toString(); // Google Cloud Storage에 저장될 파일 이름
        String ext = dto.getFile().getContentType(); // 파일의 형식 ex) JPG

        log.info("uuid : {}", uuid);

        // Cloud에 이미지 업로드
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucket, uuid)
                        .setContentType(ext)
                        .build(),
                dto.getFile().getInputStream()
        );

    }

    //다운로드
    public Blob download() {
        String file = "361f2945-4f04-41c5-a1c5-456d830e46d4";
        String location = "ttt";
        Blob blob = storage.get(bucket, file);
        blob.downloadTo(Paths.get(location));
        return blob;
    }
}
