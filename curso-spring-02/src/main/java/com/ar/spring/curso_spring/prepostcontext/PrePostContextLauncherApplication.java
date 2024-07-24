package com.ar.spring.curso_spring.prepostcontext;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/*
*  @PostConstruct --> se usa para añadir logica al bean a la hora de que el mismo es usado por el IOC Container
* @PreDestroy --> se usa para añadir logica al bean a la hora de el mismo es removido del IOC Container
* */
@Configuration
@ComponentScan
public class PrePostContextLauncherApplication {
    @Component
    class ClaseA{
        ClaseDependecia claseDependecia;

        @Autowired
        public ClaseA(ClaseDependecia claseDependecia){
            this.claseDependecia = claseDependecia;
        }

        @PostConstruct
        public void mostrarInformacionBeanClaseDependecia(){
            System.out.println(this.claseDependecia.verInformacion());
        }


        @PreDestroy
        public void dejarUtilizarBean(){
            System.out.println("Se dejo de utilizar el Bean Class A");
        }
    }

    @Component
    class ClaseDependecia{
        public String verInformacion(){
            return "Informacion de la clase dependencia";
        }
    }


    public static void main(String[] args) {
        try(var contexto = new AnnotationConfigApplicationContext(PrePostContextLauncherApplication.class)){
            Arrays.stream(contexto.getBeanDefinitionNames()).forEach(System.out::println);
        }
    }


}
