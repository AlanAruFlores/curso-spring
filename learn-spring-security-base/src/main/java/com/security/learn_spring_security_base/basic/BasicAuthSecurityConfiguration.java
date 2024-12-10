package com.security.learn_spring_security_base.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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

        //Activamos los frames para que cargue la h2 database.
        http.headers(header ->
                header.frameOptions( options -> options.sameOrigin()));
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        var user  =User.withUsername("alan") // nombre
                .password("{noop}alan") //noop --> no encripta
                .roles(ROLES.USER.name()) // rol del usuario
                .build(); //creamos el usuario

        var admin  = User.withUsername("admin")
                .password("{noop}admin")
                .roles(ROLES.ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(user,admin); // guardamos los usuarios en la memoria
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

enum ROLES{
    ADMIN,
    USER
}