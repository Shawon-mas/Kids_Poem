package com.kidspoem.kidspoem.adapter;

public class StoryTittle {
    private String title;
    private String storyId;

    public StoryTittle() {
    }

    public StoryTittle(String title, String storyId) {
        this.title = title;
        this.storyId = storyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }
}
