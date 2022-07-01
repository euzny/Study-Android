package com.example.project02_cloneapp.shop;

public class ShopDTO {
    private String img_url, name, price;

    public ShopDTO(String img_url, String name, String price) {
        this.img_url = img_url;
        this.name = name;
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
