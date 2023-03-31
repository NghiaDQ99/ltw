package com.example.stationery.dao;

import com.example.stationery.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDao {

    @Autowired
    @Qualifier("stationeryNamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Category> getAllCate() {
        try{
            String sql = "SELECT id, name, active, avatar FROM category";

            MapSqlParameterSource params = new MapSqlParameterSource();
            List<Category> listCate = jdbcTemplate.query(sql, params,
                    (rs, rowNum) -> {
                        Category category = Category.builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .active(rs.getInt("active"))
                                .avatar(rs.getString("avatar"))
                                .build();

                        return category;

                    });
            System.out.println("DAO: " + listCate);
            return listCate;
        } catch (Exception ex){
            System.out.println("DAO exception: " + ex);
        }
        return new ArrayList<>();
    }
}
