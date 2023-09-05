package com.example.study0905;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final String IMG_FOLDER_PATH = "src/main/resources/image";

    public List<String> getAllImageNames(){
        List<String> imageList = new ArrayList<>();

        //파일 목록 불러오기
        File folder = new File(IMG_FOLDER_PATH);

        if(folder.exists() && folder.isDirectory()){    
            File[] files = folder.listFiles();
            if(files != null){
                for(File file : files){
                    if(isImageFile(file.getName())){
                        imageList.add(file.getName());
                    }
                }
            }
        }

        return imageList;
    }

    private boolean isImageFile(String fileName) {
        String[] validExtensions = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};

        for (String extension : validExtensions) {
            if (fileName.toLowerCase().endsWith(extension)) {
                return true;
            }
        }

        return false;
    }

    public Resource getImage(String filename) {
        try {
            Path imagePath = Paths.get(IMG_FOLDER_PATH).resolve(filename);
            Resource resource = new UrlResource(imagePath.toUri());

            return resource.exists() ? resource : null;
        } catch (IOException e) {
            return null;
        }
    }
}
