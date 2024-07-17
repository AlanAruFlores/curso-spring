package com.ar.spring.curso_spring;

import com.ar.spring.curso_spring.game.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.ar.spring.curso_spring.game") // Escanea el paquete en el cual los componentes se encuentran ubicados (@Component)
public class App03GamingSpringBeans {
    public static void main(String[] args){
        try(AnnotationConfigApplicationContext contexto = new AnnotationConfigApplicationContext(App03GamingSpringBeans.class)){
            GamerRunner gameRunner = contexto.getBean(GamerRunner.class);
            GameRunnerMario gameRunner2 =  contexto.getBean(GameRunnerMario.class);
            GameRunnerPacman gameRunner3 =  contexto.getBean(GameRunnerPacman.class);
            gameRunner.run();
            gameRunner2.run();
            gameRunner3.run();
        }
    }
}
