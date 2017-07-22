package com.stashley.fitmate;

/**
 * Created by stephen on 7/18/17.
 */

public class Event {

    private String title;
    private String description;
    private String image;
    private String location;
    private String date;

    public Event() {

    }

    public Event(String title, String description, String image, String cost, String location, String date) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.location = location;
        this.date = date;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
