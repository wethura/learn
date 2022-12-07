package com.wethura.shardingjdbc.entity;

public class NewsEntity {

    public static final String TABLE_NAME = "news";

    protected Long id;

    protected String title;

    public NewsEntity() {
    }

    public NewsEntity(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public NewsEntity(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
