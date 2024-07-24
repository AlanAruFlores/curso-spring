package com.ar.spring.curso_spring.jakarta.cdi;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan
public class JakartaDependencyInjectionApplication {
    public static void main(String[] args) {
        try(var contexto = new AnnotationConfigApplicationContext(JakartaDependencyInjectionApplication.class)) {
            Arrays.stream(contexto.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println(contexto.getBean(ClassBean.class));
        }
    }
}
