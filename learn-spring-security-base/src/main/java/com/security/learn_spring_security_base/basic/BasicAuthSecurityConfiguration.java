package com.security.learn_spring_security_base.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BasicAuthSecurityConfiguration {



    //Metodo para desactivar el csrf
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Autenticar todas las peticiones
        http.authorizeHttpRequests(requests->{
            requests.anyRequest().authenticated();
        });

        //Desactivamos el formulario por defecto
        //http.formLogin(Customizer.withDefaults());

        //Desactivar la HttpSesion
        http.sessionManagement(session->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable()); //Desactivamos el csrf
        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // permito todos los controladores
                        .allowedMethods("*") // todos los metodos
                        .allowedOrigins("http://frontend:4321"); // origen donde permite el acceso

            }
        };
    }

}
