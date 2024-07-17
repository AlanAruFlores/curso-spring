package com.ar.spring.curso_spring.game;

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
