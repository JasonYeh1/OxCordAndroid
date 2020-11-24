package com.example.oxcord.model;

public class Song {
    private String title;
    private String URL;

    public Song(String title, String URL) {
        this.title = title;
        this.URL = URL;
    }

    public String getTitle() {
        return title;
    }

    public String getURL() {
        return URL;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
