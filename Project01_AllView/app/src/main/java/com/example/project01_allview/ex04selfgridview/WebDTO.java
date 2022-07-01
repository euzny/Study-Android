package com.example.project01_allview.ex04selfgridview;

public class WebDTO {
    private String url, name, writer,score;

    public WebDTO(String url,String name, String writer, String score) {
        this.url = url;
        this.name = name;
        this.writer = writer;
        this.score = score;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }


}
