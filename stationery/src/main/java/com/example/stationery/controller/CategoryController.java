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

        String imageSubDir = "/usr_upload/" + "/image/";
        boolean ret = new File(imageSubDir).mkdir();
        System.out.println(ret);

        String mediaPath =  "D:\\Download" + imageSubDir + "/" + mediaFileName;
        uploadFile.transferTo(new File(mediaPath));

        UploadMediaDto listImageDto = UploadMediaDto.builder()
                .mediaPath(mediaPath)
                .mediaUrl(mediaPath)
                .build();

        return ResponseEntity.ok().body(listImageDto);
    }
}
