package com.app.SpringSecurityApp.config;


import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.core.Authentication;
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

//Vamos a configurar la seguridad de nuestra aplicacion de spring boot
//@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfigNoDatabase {

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
                    //endpoint publico sin autenticacion
                    http.requestMatchers(HttpMethod.GET, "/auth/hello").permitAll();

                    //se accede si el permiso "read" esta asociado al usuario
                    http.requestMatchers(HttpMethod.GET, "/auth/hello-secured").hasAuthority("WRITE");

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
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder); //componente encoder
        provider.setUserDetailsService(userDetailsService); // componente para la base de datos

        return provider;
    }

    /**
     * Este UserDetailService vamos a comunicarlo con una base de datos en memoria por ahora
     * UserDetails: objeto donde estara la informacion de un usuario
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService(){
        //Lista con varios usuarios
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(
                User.withUsername("aruflo")
                        .password("aruflo1234")
                        .roles("ADMIN") // rol (un rol puede tener varios permisos)
                        .authorities("READ", "WRITE") // permisos
                        .build());
        userDetailsList.add(
                User.withUsername("flores")
                        .password("flores1234")
                        .roles("USER") // rol (un rol puede tener varios permisos)
                        .authorities("READ") // permisos
                        .build());


    /* un usuario
        UserDetails userDetails = User.withUsername("aruflo")
                .password("aruflo1234")
                .roles("ADMIN") // rol (un rol puede tener varios permisos)
                .authorities("READ", "WRITE") // permisos
                .build();
      */

        //cargamos en memoria los usuarios
        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance(); // no encripta, ideal, para pruebas
    }



}
