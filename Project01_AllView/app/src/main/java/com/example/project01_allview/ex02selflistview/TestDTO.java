package com.example.project01_allview.ex02selflistview;

public class TestDTO {
    private String rank, title, singer;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
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

    public TestDTO(String rank) {
        this.rank = rank;
    }
}
