package com.ar.security.spring_security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecursoHolaMundo {

    @GetMapping("/hola")
    public String holaMundo(){
        return "hola mundo";
    }
}
