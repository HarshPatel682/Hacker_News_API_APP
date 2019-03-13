package com.example.hacker_news_api_app;


import java.util.List;

/*
* This object is stored in the "list" variable found in main activity
* */
public class Article {
    public String Title;
    public String Url;
    public List<Integer> List;

    public Article(String title, String url) {
        Title = title;
        Url = url;
//        List = list;
    }
}
