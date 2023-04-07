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
}
