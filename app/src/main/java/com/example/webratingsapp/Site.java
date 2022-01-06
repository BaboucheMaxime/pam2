package com.example.webratingsapp;

public class Site implements ISites {

    private String url;
    private String rating;

    public Site(String rating, String url) {
        this.url = url;
        this.rating = rating;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public String getRating() {
        return this.rating;
    }
}