package com.stashley.fitmate;

/**
 * Created by stephen on 7/12/17.
 */

public class EventListItem {


     String city;
     String category;
     String cost;
     String description;
     String endDate;
     String endTime;
     Integer hostPhoneNumber;
     String image;
     String location;
     String startDate;
     String startTime;
     String title;

    public EventListItem() {}


    public EventListItem(String city, String category, String cost, String description, String endDate, String endTime,
                         Integer hostPhoneNumber, String image, String location, String startDate, String startTime, String title) {
        this.city = city;
        this.description = description;
        this.cost = cost;
        this.endDate = endTime;
        this.hostPhoneNumber = hostPhoneNumber;
        this.image = image;
        this.location = location;
        this.startDate = startDate;
        this.startDate = startDate;
        this.startTime = startTime;
        this.title = title;
    }



    public String getCategory() {
        return category;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }


    public String getCost() {
        return cost;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public Integer getHostPhoneNumber() {
        return hostPhoneNumber;
    }

    public String getImage() {
        return image;
    }

    public String getLargeImage(String imageUrl){
        String largeImageUrl = imageUrl.substring(0, imageUrl.length() - 6).concat("o.jpg");
        return largeImageUrl;
    }

    public String getLocation() {
        return location;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getTitle() {
        return title;
    }
}
