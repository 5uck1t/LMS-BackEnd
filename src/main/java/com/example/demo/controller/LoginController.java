package com.example.demo.controller;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.model.UlogovaniKorisnik;
import com.example.demo.service.UlogovaniKorisnikService;
import com.example.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    UlogovaniKorisnikService ulogovaniKorisnikService;

    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody UserLoginDto user) {
        UlogovaniKorisnik ulogovaniKorisnik = ulogovaniKorisnikService.findByUsernameAndPassword(user.getUsername(),
                user.getPassword()).toEntity();

        if (ulogovaniKorisnik != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(ulogovaniKorisnik.getUsername());
            return new ResponseEntity<String>(tokenUtils.generateToken(userDetails), HttpStatus.OK);
        }

        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserLoginDto newUser) {
        // Check if username already exists
        if (ulogovaniKorisnikService.findByUsername(newUser.getUsername()) != null) {
            return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
        }

        UlogovaniKorisnik korisnik = new UlogovaniKorisnik();
        korisnik.setUsername(newUser.getUsername());
        korisnik.setPassword(newUser.getPassword());

        ulogovaniKorisnikService.save(korisnik.toDto().toSaveDto());

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
}

