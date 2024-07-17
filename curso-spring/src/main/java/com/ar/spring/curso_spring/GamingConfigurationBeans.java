package com.ar.spring.curso_spring;


import com.ar.spring.curso_spring.game.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GamingConfigurationBeans {

    @Bean
    @Qualifier("mario")
    public GameConsole getMarioGame() {
        return new MarioGame();
    }

    @Bean
    @Qualifier("street")
    public GameConsole getStreetFighterGame() {
        return new StreetFighterGame();
    }

    @Bean
    @Qualifier("pacman")
    public GameConsole getPacmanGame() {
        return new PacmanGame();
    }

    @Bean
    public GamerRunner getGameRunnerMario(@Qualifier("mario") GameConsole mario){
        return new GamerRunner(mario);
    }


    @Bean
    public GamerRunner getGameRunnerStreetFighter(@Qualifier("street") GameConsole street){
        return new GamerRunner(street);
    }


    @Bean
    public GamerRunner getGameRunnerPacman(@Qualifier("pacman") GameConsole pacman){
        return new GamerRunner(pacman);
    }

}
