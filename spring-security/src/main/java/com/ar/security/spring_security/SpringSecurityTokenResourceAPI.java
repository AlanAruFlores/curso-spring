package com.ar.security.spring_security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/*Si usamos spring mvc, el token csrf ya se genera automaticamente. Pero en caso de estar usando una api rest. Necesitaremos
* obtener el token mediante la instancia "HTTPServletRequest" para realizar actualizaciones en nuestra app*/
@RestController
public class SpringSecurityTokenResourceAPI {

    @GetMapping("/csrf-token")
    public CsrfToken obtenerCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
