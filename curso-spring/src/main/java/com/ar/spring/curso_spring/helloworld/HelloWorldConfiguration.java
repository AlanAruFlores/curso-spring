package com.ar.spring.curso_spring.helloworld;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


record Persona (String nombre, Integer edad, Direccion direccion) {};
record Direccion (String direccion, String ciudad){};

@Configuration
public class HelloWorldConfiguration {

    @Bean
    public Object getService(){
        return new Object();
    }

    @Bean
    public Object getRepository(){
        return new Object();
    }

    @Bean
    public String getName(){
        return "Alan";
    }

    @Bean
    public Integer getEdad(){
        return 20;
    }

    @Bean
    public Integer getEdad2(){
        return 40;
    }


    @Bean
    public Persona getPersona(){
        return new Persona("Alan", 20, new Direccion("Direccion1", "Ciudad1"));
    }

    @Bean(name="direccion")
    @Primary //Mayor prioridad
    public Direccion getDireccion(){
        return new Direccion("Lacarra", "CABA");
    }

    @Bean(name="direccion2")
    @Qualifier("direccionQuialifer") // Alias
    public Direccion getDireccion2(){
        return new Direccion("Laguna","CABA");
    }

    @Bean
    public Persona getPersonaDosPorLlamadaDeMetodos(){
        return new Persona(getName(), getEdad(),getDireccion());
    }

    @Bean
    public Persona getPersonPorParametros(String getName, Integer getEdad2, Direccion direccion2 ){
        return new Persona(getName,getEdad2, direccion2);
    }

    @Bean
    public Persona getPersonaQualifier(String getName, Integer getEdad2, @Qualifier("direccionQuialifer") Direccion direccion2 ){
        return new Persona(getName,getEdad2, direccion2);
    }
}
