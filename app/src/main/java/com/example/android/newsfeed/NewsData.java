package com.example.android.newsfeed;

public class NewsData {
    private String mTitle;
    private String mUrl;

    public NewsData(String mTitle, String mUrl) {
        this.mTitle = mTitle;
        this.mUrl = mUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmUrl() {
        return mUrl;
    }
}
