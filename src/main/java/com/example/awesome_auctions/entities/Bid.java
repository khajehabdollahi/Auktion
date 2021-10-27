package com.example.awesome_auctions.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public class Bid {

    @Id
    private String id;
    private String userID;
    private String auctionID;
    private double price;

    public Bid() {
    }

    public Bid(String userID, String auctionID, double price) {
        this.userID = userID;
        this.auctionID = auctionID;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(String auctionID) {
        this.auctionID = auctionID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
