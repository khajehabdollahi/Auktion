package com.example.awesome_auctions.services;

import com.example.awesome_auctions.entities.Auction;
import com.example.awesome_auctions.entities.User;
import com.example.awesome_auctions.repositories.AuctionRepo;
import com.example.awesome_auctions.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AuctionService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuctionRepo auctionRepo;

    public List<Auction> findAll(String name) {
        return auctionRepo.findAll();
        //return auctionRepo.findByProductNameContains(name);
    }


    public Auction findById(String id) {
        return auctionRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Auction not found by id"));
    }



    public Auction save(Auction auction) {
        return auctionRepo.save(auction);
    }

    public User findByName(String name) {
        return userRepo.findByName(name).orElseThrow(RuntimeException::new);
    }


    public void update(String id, Auction auction) {
        var currentUser = userService.getCurrentUser();
        if (!auctionRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auction not found");
        }
        if(sameUserOrAdminOrEditor(currentUser, id)) {
            auction.setId(id);
            auctionRepo.save(auction);
        }
        else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You can not update this auction");
    }


    public void delete(String id) {
        if (!auctionRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auction not found");
        }
        auctionRepo.deleteById(id);
    }


    private Boolean sameUserOrAdminOrEditor (User currentUser, String id) {
        return (currentUser.getId().equals(id) || currentUser.getRoles().contains("ADMIN") || currentUser.getRoles().contains("EDITOR"));
    }

}
