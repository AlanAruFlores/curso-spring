package com.ar.spring.curso_spring.dependency.simple;

/*
*  Vamos a ver las diversas formas de inyectar dependencias dentro de nuestro proyecto
* */


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan //Si no indico, busca los componentes en el paquete actual
public class SimpleSpringContextLauncherApplication {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(SimpleSpringContextLauncherApplication.class)) {
            System.out.println("Spring Boot Application Context");

            //Mostramos los beans definidos
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        }
    }
}
