package com.security.learn_spring_security_base.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.UUID;

import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;

@Configuration
public class JwtSecurityConfiguration {

    //Metodo para desactivar el csrf
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Autenticar todas las peticiones
        http.authorizeHttpRequests(requests->{
            requests.anyRequest().authenticated();
        });


        //Desactivar la HttpSesion
        http.sessionManagement(session->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable()); //Desactivamos el csrf

        //Activamos los frames para que cargue la h2 database.
        http.headers(header ->
                header.frameOptions( options -> options.sameOrigin()));


        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }

    //Crear los clave pares
    @Bean
    public KeyPair keyPairs(){
        try{
            //Generamos par de claves de RSA algoritmo
            var keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            keyPairGenerator.initialize(2048); //tamaño de las claves (2048 bits)
            return keyPairGenerator.generateKeyPair();
        }catch (NoSuchAlgorithmException ex){
            throw new RuntimeException(ex);
        }
    }

    //Creamos las keys de RSA
    @Bean
    public RSAKey rsaKey(KeyPair keyPair){
       return new RSAKey
                .Builder((RSAPublicKey) keyPair.getPublic()) //pasamos la clave publica
                .privateKey(keyPair.getPrivate()) //pasamos la clave privada
                .keyID(UUID.randomUUID().toString()) //generamos un id aleatorio
                .build();

    }

    //Creamos el JWKSource
    @Bean
    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
        var jwkSet = new JWKSet(rsaKey);

        /* 1RA FORMA
        var jwkSource = new JWKSource(){
            @Override
            public List<JWK> get(JWKSelector jwkSelector, SecurityContext securityContext) throws KeySourceException {
                return jwkSelector.select(jwkSet);
            }
        };*/

        //2da forma
        JWKSource jwkSource = (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);

        return jwkSource;
    }

    //Crear el Decoder JWT para verificacion de la firma, es decir, su validacion (OJO NO PARA DESENCRIPTAR)
    @Bean
    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
        return NimbusJwtDecoder
                .withPublicKey(rsaKey.toRSAPublicKey())
                .build();
    }


    //Codificador para un especifico usuario
    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource){
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2) // tipo de bd
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION) // añadimos el script ddl por defecto
                .build(); // creamos la instancia
    }


    //Guardamos los usuarios en h2 database
    @Bean
    UserDetailsService userDetailsService(DataSource dataSource, PasswordEncoder passwordEncoder) {
        var user  = User.withUsername("alan") // nombre
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
