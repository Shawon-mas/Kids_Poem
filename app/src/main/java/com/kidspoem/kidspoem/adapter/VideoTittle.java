package com.kidspoem.kidspoem.adapter;

public class VideoTittle {
    private String title;
    private String videoId;

    public VideoTittle() {
    }

    public VideoTittle(String title, String videoId) {
        this.title = title;
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
