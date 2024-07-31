package com.ar.spring_boot_01;

/*
service-url=""
service-username=""
service-password=""
* */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/*Vamos a extraer las propiedades propuestas en el aplication properties y tambien por su perfil */
@Component
@ConfigurationProperties(prefix = "servicio")
public class ServiceConfiguration {

    private String url;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
