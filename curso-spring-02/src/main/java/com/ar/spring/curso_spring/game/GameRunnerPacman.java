package com.ar.spring.curso_spring.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunnerPacman {
    @Autowired @Qualifier("pacman")
    private GameConsole game;

    public void run(){
        System.out.println("\nEjecutando Juego: "+game);
        game.up();
        game.down();
        game.left();
        game.right();
    }}
