package com.example.stationery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pen implements Serializable {
    private int id;
    private String name;
    private float price;
    private String avatar;
    private int discount;
    private float rating;
    private int quantity;
}
