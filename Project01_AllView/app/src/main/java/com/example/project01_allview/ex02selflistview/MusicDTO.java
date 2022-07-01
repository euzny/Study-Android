package com.example.project01_allview.ex02selflistview;

public class MusicDTO {
    private int resId;
    private String title, singer, album, like;

    public MusicDTO(int resId, String title, String singer, String album, String like) {
        this.resId = resId;
        this.title = title;
        this.singer = singer;
        this.album = album;
        this.like = like;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}
