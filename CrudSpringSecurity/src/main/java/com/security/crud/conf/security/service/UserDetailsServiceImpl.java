package com.security.crud.conf.security.service;

import com.security.crud.model.User;
import com.security.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->  new UsernameNotFoundException("Not Found Username"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        user.getRoles().forEach(role -> {
            authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });

        user.getRoles()
                .stream().flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> {
                    authorityList.add(new SimpleGrantedAuthority(permission.getName().name()));
                });

        return  org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorityList)
                .build();
    }
}
