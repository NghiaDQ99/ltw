package com.example.stationery.controller;

import com.example.stationery.dto.UploadMediaDto;
import com.example.stationery.model.Category;
import com.example.stationery.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/cate")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getListCate() {
        List<Category> listCate = categoryService.getListCate();
        return ResponseEntity.ok().body(listCate);
    }

    @PostMapping("/upload/image")
    public ResponseEntity<?> uploadImage(
            @RequestParam("uFile") MultipartFile uploadFile,
            @RequestParam("user_id") String user_id
    ) throws IOException {
        String fileName = uploadFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf("."));
        if (!".jpg|.png".contains(ext)) {
            ext = ".jpg";
        }
        String mediaFileName = user_id + "_" + ext;


        String mediaPath = "/" + mediaFileName;
        Path dir = Paths.get("D:\\avatarUser" + mediaPath);
        Files.createDirectory(dir);

        uploadFile.transferTo(new File("D:\\avatarUser" + mediaPath));
        String mediaLink = "D:\\avatarUser" + mediaPath;
        UploadMediaDto listImageDto = UploadMediaDto.builder()
                .mediaPath(mediaPath)
                .mediaUrl(mediaLink)
                .build();

        return ResponseEntity.ok().body(listImageDto);
    }
}
