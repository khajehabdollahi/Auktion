package com.example.awesome_auctions.repositories;

import com.example.awesome_auctions.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

    Optional<User> findByName(String name);
    List<User> findByNameContains(String name);


    User findByEmail(String email);
}
