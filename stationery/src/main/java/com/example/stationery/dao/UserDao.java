package com.example.stationery.dao;


import com.example.stationery.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;
    @SuppressWarnings({"unchecked", "rawTypes"})
    public User loadUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "form User where username = :username";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);

        List<User> users = query.list();

        if (users != null && users.size() > 0) {
            return users.get(0);
        } else return null;
    }

    @SuppressWarnings({"unchecked", "rawTypes"})
    public boolean checkLogin(User userForm) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "form User where username = :username and password = :password";
        Query query = session.createQuery(hql);
        query.setParameter("username", userForm.getUsername());
        query.setParameter("password", userForm.getPassword());

        List<User> users = query.list();

        return users != null && users.size() > 0;
    }
}
