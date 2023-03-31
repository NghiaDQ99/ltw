package com.example.stationery.controller;

import com.example.stationery.model.Category;
import com.example.stationery.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/cate")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getListCate() {
        List<Category> listCate = categoryService.getListCate();
        System.out.println("controller: " + listCate);
        return ResponseEntity.ok().body(listCate);
    }
}
