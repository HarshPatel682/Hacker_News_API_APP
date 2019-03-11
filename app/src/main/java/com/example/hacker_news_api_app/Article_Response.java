package com.example.hacker_news_api_app;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
* This is store the values received from the HTTP request that the api client and interface will make
* I access the title and Url as of now (but I need to access the "kids" so that I can display the comments)
* */
public class Article_Response {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("deleted")
    @Expose
    private boolean deleted;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("by")
    @Expose
    private String by;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("dead")
    @Expose
    private boolean dead;
    @SerializedName("parent")
    @Expose
    private Integer parent;
    @SerializedName("kids")
    @Expose
    private List<Integer> kids = null;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("descendants")
    @Expose
    private Integer descendants;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public List<Integer> getKids() {
        return kids;
    }

    public void setKids(List<Integer> kids) {
        this.kids = kids;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDescendants() {
        return descendants;
    }

    public void setDescendants(Integer descendants) {
        this.descendants = descendants;
    }

}
