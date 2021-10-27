package com.example.awesome_auctions.controllers;


import com.example.awesome_auctions.entities.User;
import com.example.awesome_auctions.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/user")
public class UserController {


    @Autowired
    private
    UserService userService;

    @Resource(name="authenticationManager")
    private AuthenticationManager authManager;

    @GetMapping
    //@Secured({"ROLE_USER", "ROLE,ADMIN"})
    public ResponseEntity<List<User>> findUser(@RequestParam(required = false) String name) {
        var user = userService.findAll();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    // @Secured({"ROLE_EDITOR", "ROLE_ADMIN"})
    public ResponseEntity<User> findUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/whoami")
    public ResponseEntity<User> whoami(HttpServletRequest req, HttpServletResponse res) {
        User user = userService.getCurrentUser();
        if(user==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    //@Secured("ROLE_ADMIN")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping("/whoami")
    public ResponseEntity<User> whoami() {
        User user = userService.getCurrentUser();
        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }


    //@GetMapping("/a       utologin")
    @GetMapping("/autologin:{mail}")
    public User loginUser(@PathVariable String mail) {
        System.out.printf("USER EMAIl", mail);
        return userService.findByEmail(mail);
    }

    @PostMapping("/login")
    public ResponseEntity<User> securityLogin(@RequestBody User user, HttpServletRequest req) {
        //System.out.println(user.toString());
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        Authentication auth = authManager.authenticate(authReq);

        if(!auth.isAuthenticated()) {
            throw new BadCredentialsException("Wrong username or password");
        }
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

        return ResponseEntity.ok(userService.getCurrentUser());
    }


    @PutMapping("/{id}")
    //@Secured({"ROLE_EDITOR", "ROLE_ADMIN", "ROLE_USER"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable String id, @RequestBody User user) {
        userService.update(id, user);
    }


    @DeleteMapping("/{id}")
    //@Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userService.delete(id);
    }


}