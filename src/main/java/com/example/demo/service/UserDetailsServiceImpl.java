package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.UlogovaniKorisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UlogovaniKorisnikService ulogovaniKorisnikService;

    @Autowired
    private DodeljenoPravoPristupaService dodeljenoPravoPristupaService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UlogovaniKorisnik k = ulogovaniKorisnikService.findByUsername(username).toEntity();

        if (k != null) {
            ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

            for (DodeljenoPravoPristupa dodeljenoPravo : dodeljenoPravoPristupaService.findByUlogovaniKorisnikUsername(k.getUsername())) {
                grantedAuthorities.add(new SimpleGrantedAuthority(dodeljenoPravo.getPravoPristupa().getNaziv()));
            }

            return new User(k.getUsername(), k.getPassword(), grantedAuthorities);
        }
        throw new UsernameNotFoundException("nepostojeci korisnik!");
    }

}

