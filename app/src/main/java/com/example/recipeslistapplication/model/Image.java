package com.example.recipeslistapplication.model;

public class Image {
    private String imboId;
    private String url;

    public Image() {
    }

    public Image(String imboId, String url) {
        this.imboId = imboId;
        this.url = url;
    }

    public String getImboId() {
        return imboId;
    }

    public void setImboId(String imboId) {
        this.imboId = imboId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
