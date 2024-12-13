package com.security.learn_spring_security_base.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class JwtAuthenticationResource {

    private JwtEncoder jwtEncoder;

    @Autowired
    public JwtAuthenticationResource(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    //Obtenemos la informacion de una autenticacion basica
    /*
    @PostMapping("/authenticate")
    public Authentication authenticate (Authentication authentication){
        return authentication;
    }*/


    //Devolvemos el token en un registro "JwtResponse"
    @PostMapping("/authenticate")
    public JwtResponse authenticate (Authentication authentication){
        return new JwtResponse(createToken(authentication));
    }

    //Creamos el token
    private String createToken(Authentication authentication) {
       var claims =  JwtClaimsSet.builder()
                .issuer("self") // emisor (en este caso la misma aplicacion)
                .issuedAt(Instant.now()) // cuando fue emitido
                .expiresAt(Instant.now().plusSeconds(60*15)) // cuando expira el token , en este caso 15 minutos
                .subject(authentication.getName()) // sujeto
                .claim("scope", createScope(authentication)) // autoridades que tiene
                .build();

        JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);
       return jwtEncoder.encode(parameters).getTokenValue(); //obtenemos el valor del token encriptado
    }

    private String createScope(Authentication authentication) {
        // Juntamos los roles en un string separado con espacio
        return authentication.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.joining(" "));
    }
}

record JwtResponse(String token) {}
