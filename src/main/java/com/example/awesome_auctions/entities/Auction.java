package com.example.awesome_auctions.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Auction {

    @Id
    private String id;
    private String userID;
    private String productName;
    private String description;
    private String imageURL;
    private double startBid;
    private String endDate;

    public Auction() {
    }

    public Auction(String userID, String productName, String description, String imageURL, double startBid, String endDate) {
        this.productName = productName;
        this.description = description;
        this.imageURL = imageURL;
        this.startBid = startBid;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getStartBid() {
        return startBid;
    }

    public void setStartBid(double startBid) {
        this.startBid = startBid;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
