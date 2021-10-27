package com.example.awesome_auctions.repositories;

import com.example.awesome_auctions.entities.Auction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepo extends MongoRepository<Auction, String> {

}
