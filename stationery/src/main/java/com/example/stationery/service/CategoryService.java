package com.example.stationery.service;

import com.example.stationery.dao.CategoryDao;
import com.example.stationery.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryDao categoryDao;

    public List<Category> getListCate() {
        List<Category> listCate = categoryDao.getAllCate();
        System.out.println("service: " + listCate);
        return listCate;
    }
}
