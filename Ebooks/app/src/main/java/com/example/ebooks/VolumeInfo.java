package com.example.ebooks;

import com.google.gson.annotations.SerializedName;

/**
 * Created by IvanaPlamella on 19/08/17.
 */

public class VolumeInfo {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
