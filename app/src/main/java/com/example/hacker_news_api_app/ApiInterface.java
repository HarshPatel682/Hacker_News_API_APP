package com.example.hacker_news_api_app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*Used to add on the connection url found in Api Client
* gets the top stories, the specific articles*/
public interface ApiInterface {
    @GET("v0/topstories.json?print=pretty")
    Call<List<Integer>> getTopStories();
    @GET("v0/item/{articleid}.json?print=pretty")
    Call<Article_Response> getArticle(@Path("articleid") int id);
    @GET("v0/item/{commentid}.json?print=pretty")
    Call<Article_Response> getComment(@Path("commentid") int id);
}
