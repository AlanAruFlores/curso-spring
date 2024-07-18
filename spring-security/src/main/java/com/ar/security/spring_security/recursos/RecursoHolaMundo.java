package com.ar.security.spring_security.recursos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecursoHolaMundo {

    @GetMapping("/hola")
    public String holaMundo(){
        return "hola mundo";
    }
}
