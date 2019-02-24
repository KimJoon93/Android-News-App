package com.example.android.newsfeed;

public class NewsData {
    private String mTtitle;
    private String mDate;
    private String mWebUrl;
    private String mSectionName;

    public NewsData(String mTtitle, String mDate, String mWebUrl, String mSectionName) {
        this.mTtitle = mTtitle;
        this.mDate = mDate;
        this.mWebUrl = mWebUrl;
        this.mSectionName = mSectionName;
    }

    public String getmTtitle() {
        return mTtitle;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmWebUrl() {
        return mWebUrl;
    }

    public String getmSectionName() {
        return mSectionName;
    }
}

