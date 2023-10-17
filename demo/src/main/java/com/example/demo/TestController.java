package com.example.demo;

import com.google.cloud.storage.Blob;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final TestService testService;

    @PostMapping("/test")
    public ResponseEntity test(TestDto dto) throws IOException {
        testService.test(dto);
        return null;
    }

    @GetMapping("/download")
    public ResponseEntity test2() {
        Blob download = testService.download();
        log.info("여기까지");
        return ResponseEntity.ok(download);
    }
}
