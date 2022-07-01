package com.example.project02_cloneapp.chat;

public class ChatDTO {
    //DTO <= 화면에 데이터를 보여줄때 바뀌어야하는부분
    // + 데이터베이스와 매칭 시킨 후 필요한 변수를 만듦


    private String img_url, name, msg, date;

    public ChatDTO(String img_url, String name, String msg, String date) {
        this.img_url = img_url;
        this.name = name;
        this.msg = msg;
        this.date = date;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
