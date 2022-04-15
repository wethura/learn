package com.wethura.spring.orm;

import fly.core.data.annotation.Column;
import fly.core.data.annotation.JsonColumn;
import java.util.Date;
import java.util.Map;

/**
 * @author sola
 */
public class DemoEntity {

    @Column
    private String message;
    @Column
    private Date postDate;
    @Column
    private String user;
    @JsonColumn
    private Map<String, Object> json;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Map<String, Object> getJson() {
        return json;
    }

    public void setJson(Map<String, Object> json) {
        this.json = json;
    }
}
