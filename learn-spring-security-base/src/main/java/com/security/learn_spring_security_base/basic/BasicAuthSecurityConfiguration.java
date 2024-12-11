package com.security.learn_spring_security_base.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

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
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2) // tipo de bd
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION) // añadimos el script ddl por defecto
                .build(); // creamos la instancia
    }


    /*Guardar en memoria
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
    }*/


    //Guardamos los usuarios en h2 database
    @Bean
    UserDetailsService userDetailsService(DataSource dataSource, PasswordEncoder passwordEncoder) {
        var user  =User.withUsername("alan") // nombre
                //.password("{noop}alan") //noop --> no encripta
                .password("dummy")
                .passwordEncoder(str -> passwordEncoder.encode(str)) // Codifico la contraseña
                .roles(ROLES.USER.name()) // rol del usuario
                .build(); //creamos el usuario

        var admin  = User.withUsername("admin")
                //.password("{noop}admin")
                .password("admin")
                .passwordEncoder(str -> passwordEncoder.encode(str)) // Codifico la contraseña
                .roles(ROLES.ADMIN.name(), ROLES.USER.name())
                .build();

        var jdbcDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcDetailsManager.createUser(user);
        jdbcDetailsManager.createUser(admin);

        return jdbcDetailsManager; // guardamos los usuarios en la base de datos h2
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
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