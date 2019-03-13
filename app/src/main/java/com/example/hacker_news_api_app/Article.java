package com.example.hacker_news_api_app;


import java.io.Serializable;
import java.util.List;

/*
* This object is stored in the "list" variable found in main activity
* */
public class Article implements Serializable {
    public String Title;
    public String Url;
    public List<Integer> List;

    public Article(String title, String url, List<Integer> list) {
        Title = title;
        Url = url;
        List = list;
    }

    public Article(String title) {
        Title = title;
    }
}
