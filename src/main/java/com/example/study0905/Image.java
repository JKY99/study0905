package com.example.study0905;

import org.springframework.web.multipart.MultipartFile;

public record Image(String string, MultipartFile image){}