package com.app.SpringSecurityApp.config;


import com.app.SpringSecurityApp.persistence.entity.RoleEnum;
import com.app.SpringSecurityApp.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

//Vamos a configurar la seguridad de nuestra aplicacion de spring boot con base de datos
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfigDatabase {

    /**
     * Configuramos el componente "FilterChain"
     * @param httpSecurity --> es el request y es donde todos los filtros utilizan este objeto para su procesamiento
     * @return
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // desactivamos , ideal para api rest. Si es mvc si lo activamos
                .csrf(csrf-> csrf.disable())
                // activamos la autenticacion basica
                .httpBasic(Customizer.withDefaults())
                //stateless evita que la sesion se guarda en memoria y ideal para tokens
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //
                .authorizeHttpRequests(http->{
                    //Asignando permisos a los endpoints
                    //http.requestMatchers(HttpMethod.GET, "/auth/get").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/auth/get").hasAnyRole(RoleEnum.GUEST.name());

                    http.requestMatchers(HttpMethod.POST, "/auth/post").hasAuthority("WRITE");
                    http.requestMatchers(HttpMethod.PUT, "/auth/put").hasAuthority("UPDATE");
                    http.requestMatchers(HttpMethod.DELETE, "/auth/delete").hasAuthority("DELETE");
                    http.requestMatchers(HttpMethod.PATCH, "/auth/patch").hasAuthority("REFACTOR");
                   // http.anyRequest().denyAll(); // deniega el acceso a los demas endpoints no especificados
                    http.anyRequest().authenticated(); // deniega el acceso a los demas endpoints si no estan autenticados
                })
                .build();
    }

    /**
     * Configuramos el componente "AuthenticationManager"
     * @param authenticationConfiguration --> Objeto para usarlo en el authentication manager
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder); //componente encoder
        provider.setUserDetailsService(userDetailsService); // componente para la base de datos

        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // encripta
    }



}
