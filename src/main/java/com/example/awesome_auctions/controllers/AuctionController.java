package com.example.awesome_auctions.controllers;

import com.example.awesome_auctions.entities.Auction;
import com.example.awesome_auctions.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/auction")
public class AuctionController {


    @Autowired
    AuctionService auctionService;

    @GetMapping
    public ResponseEntity<List<Auction>> findAuction(@RequestParam(required = false) String name) {
        var auction = auctionService.findAll(name);
        return ResponseEntity.ok(auction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auction> findAuctionById(@PathVariable String id) {

        System.out.println("FIND BY ID");
        return ResponseEntity.ok(auctionService.findById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    //@Secured("ROLE_USER")
    public ResponseEntity<Auction> saveAuction(@RequestBody Auction auction) {
        var savedAuction = auctionService.save(auction);
        return ResponseEntity.created(URI.create("/api/v1/auction/" + savedAuction.getId())).body(savedAuction);
    }


    @PutMapping("/{id}")
    @Secured({"ROLE_USER", "ROLE_EDITOR", "ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAuction(@PathVariable String id, @RequestBody Auction auction) {
        auctionService.update(id, auction);
    }


    @DeleteMapping("/{id}")
    @Secured({"ROLE_USER", "ROLE_EDITOR", "ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuction(@PathVariable String id) {
        auctionService.delete(id);
    }













}
