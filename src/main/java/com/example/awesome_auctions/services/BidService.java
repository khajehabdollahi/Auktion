package com.example.awesome_auctions.services;

import com.example.awesome_auctions.entities.Auction;
import com.example.awesome_auctions.entities.Bid;
import com.example.awesome_auctions.repositories.AuctionRepo;
import com.example.awesome_auctions.repositories.BidRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BidService {


    @Autowired
    private BidRepo bidRepo;



    public List<Bid> findAll() {
        return bidRepo.findAll();
    }



    public Bid findById(String id) {
        return bidRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Bid not found by id"));
    }

   /* public List<Bid> findByUserID(String id) {
        return bidRepo.findByUserID(id);
    }
    */

    //public Bid findByAuctionID(String id) {
    public List<Bid> findByAuctionID(String id) {
        return bidRepo.findByAuctionID(id);
        //return bidRepo.findByAuctionID(id).orElseThrow(() ->
        //        new ResponseStatusException(HttpStatus.NOT_FOUND, "Bid not found!"));
    }



    public Bid save(Bid bid) {
        return bidRepo.save(bid);
    }


    public void update(String id, Bid bid) {
        if (!bidRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bid not found");
        }
        bid.setId(id);
        bidRepo.save(bid);
    }

    public void delete(String id) {
        if (!bidRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bid not found");
        }
        bidRepo.deleteById(id);
    }


}
