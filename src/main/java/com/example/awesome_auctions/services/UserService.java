package com.example.awesome_auctions.services;

import com.example.awesome_auctions.entities.User;
import com.example.awesome_auctions.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private UserRepo userRepo;

  /*  @Autowired
    private PasswordEncoder passwordEncoder;*/

    public List<User> findAll() {
        return userRepo.findAll();
    }


    public User findById(String id) {
        return userRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found by id"));
    }

    public User findByName(String name) {
        return userRepo.findByName(name).orElseThrow(RuntimeException::new);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User save(User user) {
        System.out.printf("USER345: ", user);
        return myUserDetailsService.addUser(user);
    }



    public User getCurrentUser() {
        // the login session is stored between page reloads,
        // and we can access the current authenticated user with this
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByEmail(email);
    }

    public void update(String id, User user) {
        var currentUser = this.getCurrentUser();
        if (!userRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        if(sameUserOrAdminOrEditor(currentUser, id)) {
            user.setId(id);
          /*  user.setPassword(passwordEncoder.encode(user.getPassword()));*/
            userRepo.save(user);
        }
        else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You can not update this user");
    }

    private Boolean sameUserOrAdminOrEditor (User currentUser, String id) {
        return (currentUser.getId().equals(id)||currentUser.getRoles().contains("ADMIN")||currentUser.getRoles().contains("EDITOR"));
    }

    public void delete(String id) {
        if (!userRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepo.deleteById(id);
    }

}
