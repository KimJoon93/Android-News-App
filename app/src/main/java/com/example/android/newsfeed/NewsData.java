package com.example.android.newsfeed;

public class NewsData {
    private String mTitle;
    private String mSection;
    private String mAuthor;
    private String mDate;
    private String mUrl;
    private String mThumbnail;
    private String mTrailTextHTML;

    public NewsData(String mTitle,String mSection,String mAuthor, String mDate, String mUrl, String mThumbnail,String mTrailTextHTML) {
        this.mTitle = mTitle;
        this.mSection = mSection;
        this.mAuthor = mAuthor;
        this.mDate = mDate;
        this.mUrl = mUrl;
        this.mThumbnail = mThumbnail;
        this.mTrailTextHTML = mTrailTextHTML;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmSection() {
        return mSection;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmThumbnail() {
        return mThumbnail;
    }

    public String getmTrailTextHTML() {
        return mTrailTextHTML;
    }
}
