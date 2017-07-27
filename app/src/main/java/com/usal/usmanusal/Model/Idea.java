package com.usal.usmanusal.Model;

import java.util.Date;

/**
 * Created by Sheriffdeen on 17/07/2017.
 */

public class Idea {
    private String title;
    private String article;
    private Long date;



    public Idea(String title, String article, Long date) {
        this.title = title;
        this.article = article;
        this.date = date;
    }
    public Idea(){
    }
    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}
