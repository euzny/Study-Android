package com.example.ex10_vendingmachine;

import android.widget.TextView;

import java.io.Serializable;

public class DrinkDTO implements Serializable {
    private String name;
    private int price, cnt;
    private TextView left_bo1;

    public DrinkDTO () {}
    public DrinkDTO(String name, int price, int cnt) {
        this.name = name;
        this.price = price;
        this.cnt = cnt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
