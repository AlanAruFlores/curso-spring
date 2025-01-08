package com.app.SpringSecurityApp.service;

import com.app.SpringSecurityApp.persistence.entity.UserEntity;
import com.app.SpringSecurityApp.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
        UserEntity userEntity = this.userRepository.findUserEntityByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("El usuario "+username+" no existe")
        );

        //Mapeamos los roles y permisos de userentity a grantedAuthority
        List<SimpleGrantedAuthority> listAuthorities = new ArrayList<>();

        //Añado sus roles
        userEntity.getSetRoles()
                .forEach(rol->{
                    listAuthorities.add(new SimpleGrantedAuthority("ROLE_"+rol.getRoleEnum().name()));
                });

        //Añado los permisos
        userEntity.getSetRoles()
                .stream().flatMap(roles->roles.getSetPermissions().stream())
                .forEach(permission -> listAuthorities.add(new SimpleGrantedAuthority(permission.getName())));



        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getIsEnabled(),
                userEntity.getAccountNoExpired(),
                userEntity.getCredentialNoExpired(),
                userEntity.getAccountNoLocked(),
                listAuthorities);
    }
}
