package com.example.study0905;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
public class ImageController {
    
    private final ImageService imageService;

    /**
     * 이미지를 가져오는 엔드포인트
     *
     * @param filename 이미지 파일 이름
     * @return 이미지 파일 리소스 또는 400 Bad Request 응답
     */
    @GetMapping("/img")
    public ResponseEntity<Resource> getImage(@RequestParam(name = "filename") String filename) {
        Resource image = imageService.getImage(filename);
        if (image != null) {
            return ResponseEntity.status(200).body(image);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    /**
     * 모든 이미지 파일 이름 목록을 가져오는 엔드포인트
     *
     * @return 이미지 파일 이름 목록 또는 400 Bad Request 응답
     */
    @GetMapping("/img/list")
    public ResponseEntity<List<String>> getAllImageNames() {
        List<String> imageNames = imageService.getAllImageNames();
        if (imageNames != null) {
            return ResponseEntity.status(200).body(imageNames);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }
}
