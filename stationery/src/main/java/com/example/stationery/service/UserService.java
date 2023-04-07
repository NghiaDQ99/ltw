package com.example.stationery.service;

import com.example.stationery.dao.UserDao;
import com.example.stationery.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;

    public User loadUserByUsername(String username) {
        return userDao.loadUserByUsername(username);
    }

    public boolean checkLogin(User user) {
        return userDao.checkLogin(user);
    }
}
