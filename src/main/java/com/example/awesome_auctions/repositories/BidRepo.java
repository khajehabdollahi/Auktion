package com.example.awesome_auctions.repositories;

import com.example.awesome_auctions.entities.Auction;
import com.example.awesome_auctions.entities.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BidRepo extends MongoRepository<Bid, String> {

    //List<Bid> findByUserID(String id);
    //List<Bid> findByAuctionID(String id);

    //Optional<Bid> findByAuctionID(String id);

    List<Bid> findByAuctionID(String auctionId);


}
