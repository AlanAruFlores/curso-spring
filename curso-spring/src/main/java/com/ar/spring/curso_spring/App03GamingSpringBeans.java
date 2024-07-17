package com.ar.spring.curso_spring;

import com.ar.spring.curso_spring.game.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App03GamingSpringBeans {

    public static void main(String[] args){
        try(AnnotationConfigApplicationContext contexto = new AnnotationConfigApplicationContext(GamingConfigurationBeans.class)){
            GamerRunner gameRunner = (GamerRunner) contexto.getBean("getGameRunnerMario");
            GamerRunner gameRunner2 = (GamerRunner) contexto.getBean("getGameRunnerStreetFighter");
            GamerRunner gameRunner3 = (GamerRunner) contexto.getBean("getGameRunnerPacman");
            gameRunner.run();
            gameRunner2.run();
            gameRunner3.run();
        }

    }
}
