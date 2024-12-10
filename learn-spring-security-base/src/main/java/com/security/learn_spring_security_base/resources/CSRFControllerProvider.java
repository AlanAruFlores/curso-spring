package com.security.learn_spring_security_base.resources;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/csrf")
public class CSRFControllerProvider {

    //Nos da un token
    @GetMapping
    public CsrfToken getTokenCSRF(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
