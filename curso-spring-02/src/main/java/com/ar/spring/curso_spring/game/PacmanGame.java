package com.ar.spring.curso_spring.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("pacman")
public class PacmanGame implements GameConsole{
    @Override
    public void up() {
        System.out.println("Arriba");
    }

    @Override
    public void down() {
        System.out.println("Abajo");
    }

    @Override
    public void left() {
        System.out.println("Izquierda");
    }

    @Override
    public void right() {
        System.out.println("Derecha");
    }
}
